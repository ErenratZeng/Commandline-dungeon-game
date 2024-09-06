package Engine;

import Engine.Model.Character;
import Engine.Model.Item;
import Engine.Model.Maze;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;


public class Engine {
    private Maze maze;
    ArrayList<Character> characters;
    ArrayList<Item> items;
    GameConfig config;


    Scanner scanner;
    public Engine(String ConfigFile) throws FileNotFoundException, JsonSyntaxException {
        Gson gson = new Gson();
        scanner = new Scanner(System.in);
        config = gson.fromJson(new FileReader(ConfigFile), GameConfig.class);
        maze = new Maze(config.getMapSize(), config);
    }

    public void print_title_screen () {
        String title = config.getTitle();
        String divider = new String(new char[title.length()]).replace("\0", "=");
        System.out.println("\n" + divider + "\n");
        System.out.println(config.getTitle());
        System.out.println("\n" + divider + "\n");
    }

    public void printMap() {
        System.out.println(this.maze.renderMaze());
    }
    public void getInput(){
        String input = scanner.nextLine();
    }

}
