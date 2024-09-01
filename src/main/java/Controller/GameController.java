package Controller;

import Model.Maze;
import Model.Player;
import View.Viewer;

public class GameController {
    private Maze maze;
    private Player player;
    private Viewer viewer;

    public GameController(Maze maze, Player player, Viewer viewer){
       this.maze = maze;
       this.player = player;
       this.viewer = viewer;

        int[] playerPosition = maze.getPlayerPosition();
        player.setPosition(playerPosition[0], playerPosition[1]);
    }

    public void StartGame(){
        //TODO
    }

    private void playerMove(int dx, int dy){
        //TODO
    }


}
