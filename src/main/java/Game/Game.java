package Game;

import Engine.Controller.InputController;
import Engine.Engine;
import Engine.Model.Direction;
import Engine.Model.Item;
import Game.Model.State.GameWinState;
import Game.Model.State.Inventory;
import Game.Model.Character.Player;
import Game.Model.State.PlayerHealth;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Game {

    static  ArrayList<String> responseControls = new ArrayList<>(Arrays.asList("accept", "decline"));
    static  ArrayList<String> movementControls = new ArrayList<>(Arrays.asList("move_up", "move_down", "move_left", "move_right"));
    static  ArrayList<String> actionControls = new ArrayList<>(Arrays.asList("inventory", "health", "map"));
    static ArrayList<String> exitControls = new ArrayList<>(List.of("quit"));

    static  Engine engine;
    static InputController inputController;
    static  Player player;

    public static void main(String[] args) {
        try {
            engine = new Engine("src/main/java/Game/GameConfig.json");
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException(e);
        }
        inputController = engine.inputController;
        player = (Player) engine.getCharacter(Player.class.getName()).get(0);

        engine.printTitleScreen();
        switch (engine.inputController.getInput("Press y to enter and n to exit", "", responseControls)) {
            case "accept":
                System.out.println("Starting game...");
                PrintBeginningStoryline();
                engine.inputController.getInput("Press y to continue...", "", responseControls);
                System.out.println("Pls type w/a/s/d for moving");
                gameLoop();
                break;
            case "decline" :
                System.out.println("Thank you for playing !");
                break;
            default:
                System.out.println("Should not happen !");
                break;
        }
    }

    public static void gameLoop() {

        while (true) {
            engine.printMap();
            String command = inputController.getInput(null, "dungeon", movementControls, actionControls, exitControls);
            Inventory inventory = (Inventory) engine.getState(Inventory.class.getName());
            GameWinState gameWinState = (GameWinState) engine.getState(GameWinState.class.getName());
            PlayerHealth playerHealth = (PlayerHealth) engine.getState(PlayerHealth.class.getName());
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
                PrintBeginningStoryline();
                System.out.println("Exiting game...");
                break;
            }
            if (gameWinState.getValue() == GameWinState.WinState.PLAYER_LOSE) {
                System.out.println("Oops you lost the Game ! Exiting game...");
                break;
            }

        }
    }

    public static void InventoryMenu (Inventory inventory) {
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
            Item item = inventory.getItem(response-1);
            item.effect(engine);
            inventory.removeItem(item);
            System.out.println(item.getName() + "has been applied !");
        }
        System.out.println("Exiting Inventory Menu !");
    }

    /**
     * Prints the beginning storyline of the game.
     */
    public static void PrintBeginningStoryline () {
        System.out.println("==================================================================================");
        System.out.println("           You wake up in a dimly lit stone chamber, cold and confused.           ");
        System.out.println("  The last thing you remember is venturing into the depths of a forgotten temple, ");
        System.out.println("         chasing after ancient whispers of a treasure hidden deep within."         );
        System.out.println("          As you stand, you notice strange symbols etched into the walls.         ");
        System.out.println("      In your hand, a worn map points to a labyrinthine maze with one promise     ");
        System.out.println("                    — \"Find the exit, or be lost forever.\"                      ");
        System.out.println("              With no other choice, you step forward into the unknown,            ");
        System.out.println("                 the sound of distant footsteps echoing behind you...             ");
        System.out.println("==================================================================================");
    }


    /**
     * Prints the ending storyline of the game.
     */
    public static void PrintEndingStoryline () {
        System.out.println("==================================================================================");
        System.out.println("         After what feels like hours of navigating the treacherous paths,         ");
        System.out.println("     you finally see a faint glimmer of light ahead. Exhausted but determined,    ");
        System.out.println("   you make your way towards it. As you cross the threshold of the final corridor,");
        System.out.println("       the maze behind you begins to collapse, sealing itself from the world.     ");
        System.out.println("       You stumble into the sunlight, breathing in the fresh air of freedom.      ");
        System.out.println("         The treasure may remain lost, but you have survived the labyrinth,       ");
        System.out.println("          Now, a new journey awaits, but that’s a story for another day.          ");
        System.out.println("==================================================================================");
    }

}
