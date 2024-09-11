package Game;

import Engine.Controller.InputController;
import Engine.Engine;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

public class Game {

    static  ArrayList<String> WelcomeScreenControls = new ArrayList<>(Arrays.asList("accept", "decline"));
    public static void main(String[] args) {
        Engine engine = null;
        try {
            engine = new Engine("src/main/java/Game/GameConfig.json");
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException(e);
        }
        engine.print_title_screen();

        InputController inputController = engine.inputController;

        switch (engine.inputController.getInput(WelcomeScreenControls)) {
            case 0 :
                System.out.println("Starting game...");
                engine.printMap();
                System.out.println("Pls type w/a/s/d for moving");
                gameLoop(engine, inputController);
                break;
            case 1 :
                System.out.println("Thank you for playing !");
                break;
            default:
                System.out.println("Should not happen !");
                break;
        }
    }

    public static void gameLoop(Engine engine, InputController inputController) {
        ArrayList<String> movementControls = new ArrayList<>(Arrays.asList("move_up", "move_down", "move_left", "move_right"));

        while (true) {
            int commandIndex = inputController.getInput(movementControls);
            String command = movementControls.get(commandIndex);
            switch (command) {
                case "move_up":
                    engine.movePlayer("w");
                    break;
                case "move_down":
                    engine.movePlayer("s");
                    break;
                case "move_left":
                    engine.movePlayer("a");
                    break;
                case "move_right":
                    engine.movePlayer("d");
                    break;
                case "quit":
                    System.out.println("Exiting game...");
                    return;
                default:
                    System.out.println("Unknown command, try again.");
                    break;
            }
        }
    }

}
