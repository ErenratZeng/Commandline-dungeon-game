package View;

import Model.Maze;

import java.util.Scanner;

public class Viewer {
    private Maze maze;
    Scanner scanner;
    public Viewer(Maze maze){
        this.maze = maze;
        this.scanner = new Scanner(System.in);
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

    public void getInput(){
        String input = scanner.nextLine();
    }

}
