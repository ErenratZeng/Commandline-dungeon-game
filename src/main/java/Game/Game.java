package Game;

import Engine.Controller.InputController;
import Engine.Engine;
import Engine.Model.Direction;
import Game.Models.Player;

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
        int[] playerStartPosition = engine.getInitialPlayerPosition();

        switch (engine.inputController.getInput(WelcomeScreenControls)) {
            case 0 :
                System.out.println("Starting game...");
                engine.printMap();
                System.out.println("Pls type w/a/s/d for moving");
                Player player = new Player(playerStartPosition[0], playerStartPosition[1], 'P');
                gameLoop(engine, inputController, player);
                break;
            case 1 :
                System.out.println("Thank you for playing !");
                break;
            default:
                System.out.println("Should not happen !");
                break;
        }
    }

    public static void gameLoop(Engine engine, InputController inputController, Player player) {
        ArrayList<String> movementControls = new ArrayList<>(Arrays.asList("move_up", "move_down", "move_left", "move_right"));

        while (true) {
            int commandIndex = inputController.getInput(movementControls);
            String command = movementControls.get(commandIndex);

            Direction direction = null;
            switch (command) {
                case "move_up":
                    direction = direction.UP;
                    break;
                case "move_down":
                    direction = direction.DOWN;
                    break;
                case "move_left":
                    direction = direction.LEFT;
                    break;
                case "move_right":
                    direction = direction.RIGHT;
                    break;
                case "quit":
                    System.out.println("Exiting game...");
                    return;
                default:
                    System.out.println("Unknown command, try again.");
                    break;
            }

            if (direction != null) {
                engine.movePlayer(player, direction);
            }

            if (engine.isGameOver()) {
                System.out.println("Game over! Exiting game...");
                break;
            }

        }
    }

}
