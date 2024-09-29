package Engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * This class defines various getter and setter methods,
 * as well as Control and Element Config classes for use with
 * the JSON file and the automatic game tester.
 *
 * Written by Christo Antony.
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

    public String getTitle() {
        return title;
    }

    public String getTitle_art() {
        return title_art;
    }

    public ArrayList<String> getStates() {
        return states;
    }

    public HashMap<Character, String> getElements() {
        return elements;
    }

    public ArrayList<ControlConfig> getControls() {
        return controls;
    }

    public HashMap<String, String> getStory() {
        return story;
    }

    public HashMap<String, char[][]> getMazes() {
        return mazes;
    }

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

        public Set<String> getInputKeys() {
            return input;
        }

        public String getName() {
            return name;
        }

        public String getDescription() { return description; }
    }

    public static class ElementConfig {
        char symbol;
        String classname;

        public char getSymbol() {
            return symbol;
        }

        public String getClassname() {
            return this.classname;
        }

    }
}



