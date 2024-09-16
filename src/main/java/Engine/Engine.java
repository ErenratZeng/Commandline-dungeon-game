package Engine;

import Engine.Controller.InputController;
import Engine.Model.*;
import Engine.Model.Character;
import Game.Models.Player;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Engine {
    public Maze maze;
    public InputController inputController;
    ArrayList<Character> characters;
    ArrayList<Item> items;
    GameConfig config;
    HashMap<String, String> inputMap;
    Scanner scanner;
    private boolean gameOver;

    public Engine(String ConfigFile) throws FileNotFoundException, JsonSyntaxException {
        Gson gson = new Gson();
        scanner = new Scanner(System.in);
        config = gson.fromJson(new FileReader(ConfigFile), GameConfig.class);
        maze = new Maze(config.getMapSize(), config);
        inputController = new InputController(config, scanner);
        gameOver = false;
    }

    public void print_title_screen () {
        String title = "Welcome to " + config.getTitle();
        String divider = new String(new char[title.length()]).replace("\0", "=");
        System.out.println(divider);
        System.out.println(title);
        System.out.println(divider);
        System.out.println(config.getTitle_art());
        System.out.println(divider);
        System.out.println("Press 'y' to enter the game");
    }

    public int[] getInitialPlayerPosition() {
        int[] defaultPosition = new int[]{0, 0};
        for (GameConfig.ElementConfig charConfig : config.getCharacters()) {
            if ("player".equals(charConfig.getName())) {
                int[][] positions = charConfig.getPositions();
                if (positions != null && positions.length > 0 && positions[0].length == 2) {
                    return positions[0];
                }
            }
        }
        return defaultPosition;
    }

    public void printMap() {
        System.out.println(this.maze.renderMaze());
    }

    public boolean moveCharacter(Player player, Direction direction) {
        int[] newCoords = player.movePlayer(direction);
        int newX = newCoords[0];
        int newY = newCoords[1];


        if (newX < 0 || newX >= maze.getRows() || newY < 0 || newY >= maze.getCols()) {
            System.out.println("Move out of bounds!");
            return false;
        }

        if (maze.getLayout()[newX][newY] != null && maze.getLayout()[newX][newY] instanceof Exit) {
            gameOver = true;
            return true;
        }

        if (maze.getLayout()[newX][newY] != null && maze.getLayout()[newX][newY].isBlocking()) {
            System.out.println("Blocked by " + maze.getLayout()[newX][newY].getSymbol());
            return false;
        }

        if (maze.getLayout()[newX][newY] != null && !(maze.getLayout()[newX][newY] instanceof Item)){
            System.out.println("Blocked by " + maze.getLayout()[newX][newY].getSymbol());
            return false;
        }

        maze.getLayout()[player.getX()][player.getY()] = null;
        player.setPosition(newX, newY);
        maze.getLayout()[newX][newY] = player;

        return true;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void movePlayer(Player player, Direction direction) {
        boolean moved = moveCharacter(player, direction);

        if (isGameOver()){
            System.out.println("Congratulations! You've reached the exit and won the game!");
            return;
        }

        if (!moved) {
            System.out.println("Invalid move or move out of bounds.");
        } else {
            System.out.println("Player moved " + direction + ".");
            printMap();
        }
    }




}
