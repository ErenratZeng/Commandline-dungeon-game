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
import java.util.List;
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

    // Method to justify the text to a given line width
    public  List<String> printStory (String story_name) {
        int lineWidth = 80;
        HashMap<String, String> stories = config.getStory();
        if (!stories.containsKey(story_name)) throw  new IllegalArgumentException("Given story name is not found in the config");
        String text = stories.get(story_name);
        String[] words = text.split(" ");
        List<String> lines = new ArrayList<>();
        List<String> currentLine = new ArrayList<>();
        int currentLineLength = 0;

        for (String word : words) {
            if (currentLineLength + word.length() + currentLine.size() <= lineWidth) {
                currentLine.add(word);
                currentLineLength += word.length();
            } else {
                lines.add(justifyLine(currentLine, lineWidth));
                currentLine.clear();
                currentLine.add(word);
                currentLineLength = word.length();
            }
        }
        // Add the last line (left-aligned, no need for justification)
        lines.add(String.join(" ", currentLine));

        String divider = "*".repeat(lineWidth);  // Top and bottom divider

        System.out.println(divider);
        for (String line : lines) {
            System.out.println(line);
        }
        System.out.println(divider);

        return lines;
    }

    // Method to justify a single line of words to the given line width
    public static String justifyLine(List<String> words, int lineWidth) {
        if (words.size() == 1) {
            return words.get(0);  // Single word line
        }

        int totalSpaces = lineWidth - words.stream().mapToInt(String::length).sum();
        int gaps = words.size() - 1;
        int spacePerGap = totalSpaces / gaps;
        int extraSpaces = totalSpaces % gaps;

        StringBuilder justifiedLine = new StringBuilder();
        for (int i = 0; i < words.size(); i++) {
            justifiedLine.append(words.get(i));
            if (i < gaps) {
                justifiedLine.append(" ".repeat(spacePerGap + (i < extraSpaces ? 1 : 0)));
            }
        }

        return justifiedLine.toString();
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
        if (elementNewXY instanceof  Transition transition) {
            transition.applyTransition(this);
        }
        else if (elementNewXY instanceof  Character tileCharacter) {
            boolean canMoveInto = tileCharacter.onInteract(this);
            if (!canMoveInto) {
                System.out.println("Move blocked by " + character.getName());
                return  false;
            }
        }
        else if (elementNewXY instanceof  Item item) {
            item.onInteract(this);
            if (item.isBlocking()) {
                System.out.println("Move bocked by " + item.getName());
                return false;
            }
        }
        else if (elementNewXY != null) {
            throw new IllegalStateException("Unexpected value: " + elementNewXY);
        }

        maze.getLayout()[character.getX()][character.getY()] = null;
        character.setPosition(newX, newY);
        System.out.println(character.getName() + " moves in the direction " + direction.name());
        maze.getLayout()[newX][newY] = character;

        return true;
    }

}
