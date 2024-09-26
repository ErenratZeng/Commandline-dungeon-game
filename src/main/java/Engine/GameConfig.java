package Engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class GameConfig {
    private String title;
    private String title_art;
    private ArrayList<String> states;
    private HashMap<Character, String> elements;
    private ArrayList<ControlConfig> controls;
    private ArrayList<ElementConfig> block;
    private String beginning_storyline;
    private String ending_storyline;
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

    public String getBeginning_storyline() {
        return beginning_storyline;
    }

    public String getEnding_storyline() {
        return ending_storyline;
    }
    public HashMap<String, char[][]> getMazes() {
        return mazes;
    }

    public Character getEmptyElement() {
        return emptyElement;
    }

    public static class ControlConfig {

        public String name;
        Set<String> input;

        public Set<String> getInputKeys() {
            return input;
        }

        public String getName() {
            return name;
        }
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



