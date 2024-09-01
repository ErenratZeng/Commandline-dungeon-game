package Controller;

import Model.Maze;
import Model.Player;
import View.Viewer;

public class Game {
    public static void main(String[] args) {
        Maze maze = new Maze();
        maze.load_map("src/main/java/MapDir/map1.json");


        Player player = new Player(10);
        Viewer viewer = new Viewer(maze);
        viewer.printMap();

        //TODO
//        GameController gameController = new GameController(maze, player, viewer);
//        gameController.StartGame();

    }
}
