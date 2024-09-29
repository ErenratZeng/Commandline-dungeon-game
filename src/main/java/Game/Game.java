package Game;

import Engine.Controller.InputController;
import Engine.Engine;
import Engine.Model.Direction;
import Engine.Model.Item;
import Game.Model.State.GameLevel;
import Game.Model.State.GameWinState;
import Game.Model.State.Inventory;
import Game.Model.Character.Player;
import Game.Model.State.PlayerHealth;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This buildable class handles much of the game's interface and links
 * together code integral to running the game.
 *
 * Written by Christo Antony, Qiutong Zeng, Xiaotian Cheng.
 */
public class Game {

    /**
     * The sets of important controls initialised as strings for later reading
     * in other functions and such.
     */
    static ArrayList<String> responseControls = new ArrayList<>(Arrays.asList("accept", "decline"));
    static ArrayList<String> movementControls = new ArrayList<>(Arrays.asList("move_up", "move_down", "move_left", "move_right"));
    static ArrayList<String> actionControls = new ArrayList<>(Arrays.asList("inventory", "health", "map", "help"));
    static ArrayList<String> exitControls = new ArrayList<>(List.of("quit"));

    static Engine engine;
    static InputController inputController;
    static Player player;

    public static void main(String[] args) {
        // Read or throw an error depending on the existence of the JSON file.
        try {
            engine = new Engine("src/main/java/Game/GameConfig.json");
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException(e);
        }

        // Initialise the settings and either begin the game or terminate.
        engine.setCurrentMaze("level_1");
        inputController = engine.inputController;
        engine.printTitleScreen();
        player = engine.getCharacter(Player.class).get(0);
        switch (engine.inputController.getInput("Press y to enter and n to exit", "", responseControls)) {
            case "accept":
                System.out.println("Starting game...");
                engine.printTextBlock("beginning");
                engine.inputController.getInput("Press y to continue...", "", responseControls);
                System.out.println("Pls type w/a/s/d for moving, type help for getting help.");
                gameLoop();
                break;
            case "decline":
                System.out.println("Closing the game...");
                break;
            default:
                System.out.println("Something went wrong! Terminating...");
                break;
        }
    }

    /**
     * This method is set to constantly run and handle player input as well as
     * terminating the game once the player wins or loses.
     */
    public static void gameLoop() {
        engine.setOnMazeChange(() -> player = engine.getCharacter(Player.class).get(0));
        Inventory inventory = engine.getState(Inventory.class);
        GameWinState gameWinState = engine.getState(GameWinState.class);
        PlayerHealth playerHealth = engine.getState(PlayerHealth.class);
        while (true) {
            engine.printMap();
            String command = inputController.getInput(null, "dungeon", movementControls, actionControls, exitControls);
            switch (command) {
                case "move_up":
                    engine.moveCharacter(player, Direction.UP);
                    break;
                case "move_down":
                    engine.moveCharacter(player, Direction.DOWN);
                    break;
                case "move_left":
                    engine.moveCharacter(player, Direction.LEFT);
                    break;
                case "move_right":
                    engine.moveCharacter(player, Direction.RIGHT);
                    break;
                case "inventory":
                    InventoryMenu(inventory);
                    break;
                case "health":
                    playerHealth.print();
                    break;
                case "help":
                    HelpMenu();
                    break;
                case "map":
                    Engine.printHeaderBlock("Map Header");
                    engine.printMap();
                    break;
                case "quit":
                    System.out.println("Exiting game...");
                    return;
                default:
                    System.out.println("Unknown command, try again.");
                    break;
            }

            if (gameWinState.getValue() == GameWinState.WinState.PLAYER_WIN) {
                System.out.println("You won!");
                engine.printTextBlock("ending");
                System.out.println("Exiting game...");
                break;
            }
            if (gameWinState.getValue() == GameWinState.WinState.PLAYER_LOSE) {
                System.out.println("The game is lost. Exiting game...");
                break;
            }

        }
    }

    /**
     * Handles player interaction with the inventory system.
     *
     * @param inventory The current inventory.
     */
    public static void InventoryMenu(Inventory inventory) {
        Engine.printHeaderBlock("Inventory");
        while (true) {
            int inventorySize = inventory.getValue().size();
            if (inventorySize == 0) {
                System.out.println("Your inventory is empty.");
                break;
            }
            inventory.print();
            int response = inputController.getIntegerInput(0, inventory.getValue().size(), "Enter an item to use (0 to exit).", "Inventory");
            if (response == 0) {
                break;
            }
            Item item = inventory.getItem(response - 1);
            item.effect(engine);
            System.out.println(item.getName() + "has been applied!");
        }
        System.out.println("Returning to map display...");
    }

    /**
     * Displays the help menu, prints descriptions, and prompts the user for input.
     *
     * @author Xiaotian Cheng
     */
    public static void HelpMenu() {
        Engine.printHeaderBlock("Help Menu");
        engine.printHelpMenu();
        engine.inputController.getInput("Press y to continue...", "", responseControls);
    }


}
