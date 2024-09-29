package Engine;

import Engine.Controller.InputController;
import Engine.Model.*;
import Engine.Model.Character;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Engine {
    public Maze currentMaze;
    public InputController inputController;
    HashMap<String, Maze> mazes;
    HashMap<String, ArrayList<Character>> characters;
    HashMap<String, ArrayList<Item>> items;
    HashMap<String, GameState<?>> gameStates;
    GameConfig config;
    Scanner scanner;

    Runnable onMazeChange;

    public Engine(String ConfigFile) throws FileNotFoundException, JsonSyntaxException {
        Gson gson = new Gson();
        scanner = new Scanner(System.in);
        config = gson.fromJson(new FileReader(ConfigFile), GameConfig.class);
        inputController = new InputController(config, scanner);
        initializeMazeHashMap();  // set up the maze map
        initializeGameState();    // set up the game states
    }

    public void setCurrentMaze(String maze_name) {
        if (!mazes.containsKey(maze_name))
            throw new IllegalArgumentException("Error setting new maze: Maze name %s doesn't exists".formatted(maze_name));
        this.currentMaze = mazes.get(maze_name);
        initializeCharactersAndItems(); //Initialize the corresponding Character and Items
        if (onMazeChange != null) onMazeChange.run(); // run any callbacks the game has to do when the maze changes
    }

    public void setOnMazeChange(Runnable runnable) {
        this.onMazeChange = runnable;
    }

    public void printTitleScreen() {
        String title = "Welcome to " + config.getTitle();
        printHeaderBlock(title);
        System.out.println(config.getTitle_art());
    }

    public static void printHeaderBlock(String title) {
        String divider = new String(new char[title.length()]).replace("\0", "=");
        System.out.println(divider);
        System.out.println(title);
        System.out.println(divider);
    }

    public <T extends Character> ArrayList<T> getCharacter(Class<T> character) {
        return characters.get(character.getName())
                .stream()
                .filter(character::isInstance)
                .map(character::cast)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public <T extends GameState<?>> T getState(Class<T> item) {
        GameState<?> state = gameStates.get(item.getName());
        if (item.isInstance(state)) return item.cast(state);
        return null;
    }

    // Method to justify the text to a given line width
    public void printTextBlock(String story_name) {
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

    }

    /**
     * Prints control descriptions and maze legends from the game configuration.
     * <p>
     * The method prints available controls with their input keys and descriptions,
     * and the legend elements in the maze with their short class names.
     *
     * @author Xiaotian Cheng
     */
    public void printHelpMenu() {
        System.out.println("Controls in the Game:");
        for (GameConfig.ControlConfig control : config.getControls()) {
            if(control.getDescription() != null){
                String inputs = String.join(", ", control.getInputKeys());
                System.out.println(inputs + " = " + control.getDescription());
            }
        }
        HashMap<java.lang.Character, String> elements = config.getElements();
        Pattern pattern = Pattern.compile("\\.(\\w+)$");

        System.out.println("\nLegends in the Maze:");
        for (java.lang.Character key : elements.keySet()) {
            String fullClassName = elements.get(key);
            Matcher matcher = pattern.matcher(fullClassName);

            if (matcher.find()) {
                String shortName = matcher.group(1);
                System.out.println("\"" + key + "\" : " + shortName);
            }
        }
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
        System.out.println(this.currentMaze.renderMaze());
    }

    public void moveCharacter(Character character, Direction direction) {
        int[] newCoords = character.moveCharacter(direction);
        int newX = newCoords[0];
        int newY = newCoords[1];


        if (newX < 0 || newX >= currentMaze.getCols() || newY < 0 || newY >= currentMaze.getRows()) {
            System.out.println("Move out of bounds!");
            return;
        }
        MazeElement elementNewXY = currentMaze.getElement(newX, newY);
        if (elementNewXY instanceof Transition transition) {
            transition.applyTransition(this);
            return;
        } else if (elementNewXY instanceof Character tileCharacter) {
            boolean canMoveInto = tileCharacter.onInteract(this);
            if (!canMoveInto) return;
        } else if (elementNewXY instanceof Item item) {
            item.onInteract(this);
            if (item.isBlocking()) {
                System.out.println("Move bocked by " + item.getName());
                return;
            }
        } else if (elementNewXY != null) {
            throw new IllegalStateException("Unexpected value: " + elementNewXY);
        }

        currentMaze.setElementAt(character.getX(), character.getY(), null);
        character.setPosition(newX, newY);
        currentMaze.setElementAt(newX, newY, character);
        System.out.println(character.getName() + " moves in the direction " + direction.name());

    }


    private void initializeMazeHashMap() {
        this.mazes = new HashMap<>();
        for (Map.Entry<String, char[][]> mazeSet : config.getMazes().entrySet()) {
            String mazeName = mazeSet.getKey();
            char[][] mazeDesign = mazeSet.getValue();
            Maze maze = new Maze(mazeDesign, config.getElements(), config.getEmptyElement());
            mazes.put(mazeName, maze);
        }
    }

    private void initializeGameState() {
        gameStates = new HashMap<>();
        try {
            for (String c : config.getStates()) {
                Class<?> aClass = Class.forName(c);
                Object elementObject = aClass.getDeclaredConstructor().newInstance();
                if (!(elementObject instanceof GameState<?>))
                    throw new IllegalArgumentException("The Game State class is not appropriate for " + c);
                gameStates.put(c, (GameState<?>) elementObject);
            }
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {
            System.err.println("Error while initializing state variable: " + e.getMessage());
        }
    }

    private void initializeCharactersAndItems() {
        // add the characters and the items to a hashmap for quick lookup
        characters = new HashMap<>();
        items = new HashMap<>();
        for (int i =0; i <currentMaze.getRows(); i++) {
            for (int j=0; j< currentMaze.getCols(); j++) {
                MazeElement e = currentMaze.getElement(j, i);
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

}
