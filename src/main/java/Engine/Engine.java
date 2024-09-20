package Engine;

import Engine.Controller.InputController;
import Engine.Model.*;
import Engine.Model.Character;
import Game.Model.Character.Player;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class Engine {
    public Maze maze;
    public InputController inputController;
    HashMap<String, ArrayList<Character>> characters;
    HashMap<String, ArrayList<Item>> items;
    HashMap<String, GameState<?>>  gameStates;
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
        gameStates = initializeGameState();
        // add the characters and the items to a hashmap for quick lookup
        characters = new HashMap<>();
        items = new HashMap<>();
        MazeElement[][] layout = maze.getLayout();
        for (MazeElement[] es : layout) {
            for (MazeElement e : es) {
                String classname = null;
                if (e != null) classname = e.getClass().getName();
                if (e instanceof Item item) {
                    ArrayList<Item> itemList = items.getOrDefault(classname, new ArrayList<>());
                    itemList.add(item);
                    items.put(classname, itemList);
                } else if (e instanceof Character character) {
                    ArrayList<Character> characterList = characters.getOrDefault(classname, new ArrayList<>());
                    characterList.add(character);
                    characters.put(classname, characterList);

                }
            }
        }
    }

    private HashMap<String, GameState<?>> initializeGameState() {
        HashMap<String, GameState<?>> states = new HashMap<>();
        try {
            for (String c : config.getStates()) {
                Class<?> aClass = Class.forName(c);
                Object elementObject = aClass.getDeclaredConstructor().newInstance();
                if (!(elementObject instanceof GameState<?>))
                    throw new IllegalArgumentException("The Game State class is not appropriate for " + c);
                states.put(c, (GameState<?>) elementObject);
            }
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {
            System.err.println("Error while initializing state variable: " + e.getMessage());
        }
        return  states;
    }

    public void printTitleScreen() {
        String title = "Welcome to " + config.getTitle();
        printHeaderBlock(title);
        System.out.println(config.getTitle_art());
    }

    public static  void printHeaderBlock(String title) {
        String divider = new String(new char[title.length()]).replace("\0", "=");
        System.out.println(divider);
        System.out.println(title);
        System.out.println(divider);
    }

    public ArrayList<Character> getCharacter(String characterName) {
        return characters.get(characterName);
    }

    public ArrayList<Character> getItems (String itemName) {
        return characters.get(itemName);
    }

    public GameState<?> getState (String stateName) {
        return gameStates.get(stateName);
    }

    public void printMap() {
        System.out.println(this.maze.renderMaze());
    }

    public boolean moveCharacter(Character character, Direction direction) {
        int[] newCoords = character.moveCharacter(direction);
        int newX = newCoords[0];
        int newY = newCoords[1];


        if (newX < 0 || newX >= maze.getRows() || newY < 0 || newY >= maze.getCols()) {
            System.out.println("Move out of bounds!");
            return false;
        }
        MazeElement elementNewXY =  maze.getLayout()[newX][newY];
        switch (elementNewXY) {
            case null:
                break;
            case Transition transition :
                transition.applyTransition(this);
                break;
            case Character tileCharacter:
                boolean canMoveInto = tileCharacter.onInteract(this);
                if (!canMoveInto) {
                    System.out.println("Move blocked by " + character.getName());
                    return  false;
                }
                break;
            case Item item:
                item.onInteract(this);
                if (item.isBlocking()) {
                    System.out.println("Move bocked by " + item.getName());
                    return false;
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + elementNewXY);
        }

        maze.getLayout()[character.getX()][character.getY()] = null;
        character.setPosition(newX, newY);
        System.out.println(character.getName() + " moves in the direction " + direction.name());
        maze.getLayout()[newX][newY] = character;

        return true;
    }

}
