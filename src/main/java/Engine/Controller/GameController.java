package Engine.Controller;

import Engine.Model.Maze;
import Game.Models.Player;
import Engine.Engine;

import java.util.Scanner;

public class GameController {
    private Maze maze;
    private Player player;
    private Engine viewer;
    private Scanner scanner;

    public GameController(Maze maze, Player player, Engine viewer){
       this.maze = maze;
       this.player = player;
       this.viewer = viewer;
       this.scanner = new Scanner(System.in);

        int[] playerPosition = maze.getPlayerPosition();
        player.setPosition(playerPosition[0], playerPosition[1]);
    }

    public void StartGame(){
        //TODO
         while (true){
             viewer.printMap();
             System.out.println("Enter w/a/s/d for move");
             String move = scanner.nextLine();

             //TODO it was fixed coor for current stage, it need to be changed later
             if (move.equals("w")){
                 playerMove(-1, 0);
             } else if (move.equals("a")) {
                 playerMove(0, -1);
             } else if (move.equals("s")) {
                 playerMove(1, 0);
             } else if (move.equals("d")) {
                 playerMove(0, 1);
             } else {
                 System.out.println("Invalid move. Use w/a/s/d to move.");
             }

         }
         
         
         
    }

    private void playerMove(int dx, int dy){
        //TODO
        //Update player position on "waze" and print when player encounter enemy or items
    }


}
