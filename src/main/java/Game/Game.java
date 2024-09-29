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
 * The main class for the Game. It initializes the game engine, handles user input, and controls the game flow.
 * The player interacts with the game by moving, using the inventory, and checking health, among other actions.
 *
 * @author Christo Antony
 * @author Qiutong Zeng
 * @author Xiaotian Cheng
 */
public class Game {

    static ArrayList<String> responseControls = new ArrayList<>(Arrays.asList("accept", "decline"));
    static ArrayList<String> movementControls = new ArrayList<>(Arrays.asList("move_up", "move_down", "move_left", "move_right"));
    static ArrayList<String> actionControls = new ArrayList<>(Arrays.asList("inventory", "health", "map", "help"));
    static ArrayList<String> exitControls = new ArrayList<>(List.of("quit"));

    static Engine engine;
    static InputController inputController;
    static Player player;

    /**
     * The main entry point for the game.
     * Initializes the game engine, sets up the player, and handles the initial input to start or exit the game.
     *
     * @param args command-line arguments (not used in this game).
     */
    public static void main(String[] args) {
        try {
            engine = new Engine("src/main/java/Game/GameConfig.json");
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException(e);
        }
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
                System.out.println("Thank you for playing !");
                break;
            default:
                System.out.println("Should not happen !");
                break;
        }
    }

    /**
     * The main game loop that continuously listens for user input and updates the game state accordingly.
     * It handles movement, inventory management, checking player health, displaying the map, and exiting the game.
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
                System.out.println("Yay you won the game !");
                engine.printTextBlock("ending");
                System.out.println("Exiting game...");
                break;
            }
            if (gameWinState.getValue() == GameWinState.WinState.PLAYER_LOSE) {
                System.out.println("Oops you lost the Game ! Exiting game...");
                break;
            }

        }
    }

    /**
     * Displays the inventory menu, allowing the player to use items from their inventory.
     * The inventory is printed, and the player can choose an item to apply its effect.
     *
     * @param inventory the player's inventory containing items to be used.
     */
    public static void InventoryMenu(Inventory inventory) {
        Engine.printHeaderBlock("Inventory");
        while (true) {
            int inventorySize = inventory.getValue().size();
            if (inventorySize == 0) {
                System.out.println("Oops the inventory is empty !");
                break;
            }
            inventory.print();
            int response = inputController.getIntegerInput(0, inventory.getValue().size(), "Enter an item to use (0 to exit)", "Inventory");
            if (response == 0) {
                break;
            }
            Item item = inventory.getItem(response - 1);
            item.effect(engine);
            System.out.println(item.getName() + "has been applied !");
        }
        System.out.println("Exiting Inventory Menu !");
    }

    /**
     * Displays the help menu, prints descriptions, and prompts the user for input.
     */
    public static void HelpMenu() {
        Engine.printHeaderBlock("Help Menu");
        engine.printHelpMenu();
        engine.inputController.getInput("Press y to continue...", "", responseControls);
    }


}
