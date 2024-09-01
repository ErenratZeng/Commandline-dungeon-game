package Controller;

import Model.Maze;
public class Game {
    public static void main(String[] args) {
        Maze maze = new Maze();
        maze.load_map("src/main/java/MapDir/map1.json");
        maze.printMap();

    }
}
