package View;

import Model.Maze;

public class Viewer {
    private Maze maze;
    public Viewer(Maze maze){
        this.maze = maze;
    }
    public void printMap() {
        if (maze.getLayout() != null) {
            for (String[] row : maze.getLayout()) {
                for (String tile : row) {
                    System.out.print(tile);
                }
                System.out.println();
            }
        } else {
            System.out.println("Map is not loaded properly.");
        }
    }
}
