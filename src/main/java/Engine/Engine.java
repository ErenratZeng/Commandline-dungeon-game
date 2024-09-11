package Engine;

import Engine.Controller.InputController;
import Engine.Model.Character;
import Engine.Model.Item;
import Engine.Model.Maze;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;


public class Engine {
    private Maze maze;
    public InputController inputController;
    ArrayList<Character> characters;
    ArrayList<Item> items;
    GameConfig config;
    HashMap<String, String> inputMap;
    Scanner scanner;

    public Engine(String ConfigFile) throws FileNotFoundException, JsonSyntaxException {
        Gson gson = new Gson();
        scanner = new Scanner(System.in);
        config = gson.fromJson(new FileReader(ConfigFile), GameConfig.class);
        maze = new Maze(config.getMapSize(), config);
        inputController = new InputController(config, scanner);
    }

    public void print_title_screen () {
        String title = "Welcome to " + config.getTitle();
        String divider = new String(new char[title.length()]).replace("\0", "=");
        System.out.println(divider);
        System.out.println(title);
        System.out.println(divider);
        System.out.println(config.getTitle_art());
    }

    public void printMap() {
        System.out.println(this.maze.renderMaze());
    }

    public void movePlayer(String direction) {
        boolean moved = maze.movePlayer(direction);

        if (!moved) {
            System.out.println("Invalid move or move out of bounds.");
        } else {
            System.out.println("Player moved " + direction + ".");
            printMap();
        }
    }

}
