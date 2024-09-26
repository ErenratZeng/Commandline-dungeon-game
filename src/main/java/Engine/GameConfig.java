package Engine;

import java.util.ArrayList;
import java.util.Set;

public class GameConfig {
    private String title;
    private String title_art;
    private ArrayList<String> states;
    private ArrayList<ElementConfig> characters;
    private ArrayList<ElementConfig> items;
    private ArrayList<ElementConfig> transitions;
    private ArrayList<String> map;
    private ArrayList<ControlConfig> controls;
    private ArrayList<ElementConfig> block;
    private String beginning_storyline;
    private String ending_storyline;

    private int[] map_size;

    public String getTitle() {
        return title;
    }

    public String getTitle_art() {
        return title_art;
    }

    public ArrayList<ElementConfig> getCharacters() {
        return characters;
    }

    public ArrayList<ElementConfig> getItems() {
        return items;
    }

    public ArrayList<String> getStates() {
        return states;
    }

    public ArrayList<ElementConfig> getTransitions() {
        return transitions;
    }

    public ArrayList<ElementConfig> getBlock() {
        return block;
    }

    public ArrayList<String> getMap() {
        return map;
    }

    public int[] getMapSize() {
        return map_size;
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
        int[][] positions;
        String classname;


        public char getSymbol() {
            return symbol;
        }

        public int[][] getPositions() {
            return positions;
        }

        public String getClassname() {
            return this.classname;
        }

    }
}



