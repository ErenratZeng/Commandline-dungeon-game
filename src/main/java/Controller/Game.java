package Controller;

import Model.Maze;
import View.Viewer;

public class Game {
    public static void main(String[] args) {
        Maze maze = new Maze();
        maze.load_map("src/main/java/MapDir/map1.json");

        Viewer viewer = new Viewer(maze);
        viewer.printMap();

    }
}
