package Engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * This class defines various getter and setter methods,
 * as well as Control and Element Config classes for use with
 * the JSON file and the automatic game tester.
 *
 * @author Christo Antony.
 */
public class GameConfig {
    private String title;
    private String title_art;
    private ArrayList<String> states;
    private HashMap<Character, String> elements;
    private ArrayList<ControlConfig> controls;
    private ArrayList<ElementConfig> block;
    private HashMap<String, String> story;
    private HashMap<String, char[][]> mazes;


    private Character emptyElement;

    /**
     * Gets the game title.
     *
     * @return the title of the game.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets the title art of the game.
     *
     * @return the ASCII art representing the title.
     */
    public String getTitle_art() {
        return title_art;
    }

    /**
     * Gets the list of game states.
     *
     * @return an ArrayList of strings representing the game states.
     */
    public ArrayList<String> getStates() {
        return states;
    }

    /**
     * Gets the elements of the game, where each element is mapped to its class.
     *
     * @return a HashMap with characters as keys representing the element symbols and strings representing the element class.
     */
    public HashMap<Character, String> getElements() {
        return elements;
    }

    /**
     * Gets the controls configuration for the game.
     *
     * @return an ArrayList of ControlConfig objects representing the controls of the game.
     */
    public ArrayList<ControlConfig> getControls() {
        return controls;
    }

    /**
     * Gets the story elements of the game, where story names are mapped to their text content.
     *
     * @return a HashMap with strings representing story names and strings representing story content.
     */
    public HashMap<String, String> getStory() {
        return story;
    }

    /**
     * Gets the maze designs from the configuration, where each maze is represented as a 2D array of characters.
     *
     * @return a HashMap with strings representing maze names and 2D character arrays representing maze layouts.
     */
    public HashMap<String, char[][]> getMazes() {
        return mazes;
    }

    /**
     * Gets the empty element character symbol, used to represent empty spaces in the maze.
     *
     * @return the character symbol for empty elements.
     */
    public Character getEmptyElement() {
        return emptyElement;
    }

    /**
     * This class is specifically concerned with handling controls in relation
     * to the game tester.
     */
    public static class ControlConfig {

        public String name;
        public String description;
        Set<String> input;

        /**
         * Gets the input keys associated with this control.
         *
         * @return a set of strings representing the input keys.
         */
        public Set<String> getInputKeys() {
            return input;
        }

        /**
         * Gets the name of the control.
         *
         * @return the name of the control as a string.
         */
        public String getName() {
            return name;
        }

        /**
         * Gets the description of the control.
         *
         * @return the description of the control as a string.
         */
        public String getDescription() { return description; }
    }

    /**
     * This class defines elements in the game, associating each element symbol with a class name.
     */
    public static class ElementConfig {
        char symbol;
        String classname;

        /**
         * Gets the symbol representing this element.
         *
         * @return the character symbol of the element.
         */
        public char getSymbol() {
            return symbol;
        }

        /**
         * Gets the class name associated with this element.
         *
         * @return the fully qualified class name of the element as a string.
         */
        public String getClassname() {
            return this.classname;
        }

    }
}



