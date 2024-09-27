package Game;

import Engine.Engine;
import Engine.Model.Direction;
import Game.Model.Character.Player;

import java.util.Arrays;

public class GameTester {

    public static void main(String[] args) {
        try {
            Engine engine = new Engine("src/main/java/Game/GameConfig.json");
            engine.setCurrentMaze("level_1");

            Player player = new Player(1, 0);
            engine.currentMaze.getLayout()[player.getX()][player.getY()] = player;

            System.out.println("Initial Map:");
            engine.printMap();

            simulatePlayerInput(engine, player, Direction.UP);
            simulatePlayerInput(engine, player, Direction.LEFT);
            simulatePlayerInput(engine, player, Direction.DOWN);
            simulatePlayerInput(engine, player, Direction.RIGHT);

            moveLongDistance(engine, player);

            String[] expectedMap = {
                    "|.|.|.|#|#|#|#|#|#|#|#|#|",
                    "|.|.|.|.|.|.|.|.|.|.|.|#|",
                    "|#|#|#|#|#|#|#|P|.|#|#|#|",
                    "|#|.|.|.|.|.|.|.|#|.|.|#|",
                    "|#|#|#|#|.|.|#|#|#|#|.|#|",
                    "|#|.|.|.|S|.|.|.|.|.|.|#|",
                    "|#|#|#|#|#|#|#|#|#|#|E|#|",
                    "|.|.|.|.|.|.|.|.|.|.|G|#|",
                    "|#|#|#|#|#|#|#|#|#|#|.|Z|"
            };

            if (validateMap(engine, expectedMap)) {
                System.out.println("Final Test Passed: Map matches expected state.");
            } else {
                System.err.println("Final Test Failed: Map does not match expected state.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static void simulatePlayerInput(Engine engine, Player player, Direction direction) {
        engine.moveCharacter(player, direction);
        System.out.println("After moving " + direction + ":");
        engine.printMap();
    }

    private static void moveLongDistance(Engine engine, Player player) {
        simulatePlayerInput(engine, player, Direction.RIGHT);
        simulatePlayerInput(engine, player, Direction.RIGHT);
        simulatePlayerInput(engine, player, Direction.RIGHT);
        simulatePlayerInput(engine, player, Direction.RIGHT);
        simulatePlayerInput(engine, player, Direction.RIGHT);
        simulatePlayerInput(engine, player, Direction.RIGHT);
        simulatePlayerInput(engine, player, Direction.DOWN);
    }

    private static boolean validateMap(Engine engine, String[] expectedMap) {
        String[] actualMap = engine.currentMaze.renderMaze().split("\n");
        return Arrays.equals(expectedMap, actualMap);
    }
}