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

    public void setOnMazeChange (Runnable runnable) {
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

    public ArrayList<Character> getCharacter(String characterName) {
        return characters.get(characterName);
    }

    public ArrayList<Character> getItems(String itemName) {
        return characters.get(itemName);
    }

    public GameState<?> getState(String stateName) {
        return gameStates.get(stateName);
    }

    public String getStoryline (String part) {
        if ("beginning_storyline".equals(part)) {
            return config.getBeginning_storyline();
        } else if ("ending_storyline".equals(part)) {
            return config.getEnding_storyline();
        } else {
            return null;
        }
    }

    public void printMap() {
        System.out.println(this.currentMaze.renderMaze());
    }

    public void moveCharacter(Character character, Direction direction) {
        int[] newCoords = character.moveCharacter(direction);
        int newX = newCoords[0];
        int newY = newCoords[1];


        if (newX < 0 || newX >= currentMaze.getRows() || newY < 0 || newY >= currentMaze.getCols()) {
            System.out.println("Move out of bounds!");
            return;
        }
        MazeElement elementNewXY = currentMaze.getLayout()[newX][newY];
        if (elementNewXY instanceof Transition transition) {
            transition.applyTransition(this);
            return;
        } else if (elementNewXY instanceof Character tileCharacter) {
            boolean canMoveInto = tileCharacter.onInteract(this);
            if (!canMoveInto) {
                System.out.println("Move blocked by " + character.getName());
                return;
            }
        } else if (elementNewXY instanceof Item item) {
            item.onInteract(this);
            if (item.isBlocking()) {
                System.out.println("Move bocked by " + item.getName());
                return;
            }
        } else if (elementNewXY != null) {
            throw new IllegalStateException("Unexpected value: " + elementNewXY);
        }

        currentMaze.getLayout()[character.getX()][character.getY()] = null;
        character.setPosition(newX, newY);
        System.out.println(character.getName() + " moves in the direction " + direction.name());
        currentMaze.getLayout()[newX][newY] = character;
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
        MazeElement[][] layout = currentMaze.getLayout();
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

}
