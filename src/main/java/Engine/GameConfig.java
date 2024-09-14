package Engine;

import java.util.ArrayList;
import java.util.Set;

public class GameConfig {
    private String title;
    private String title_art;
    private ArrayList<ElementConfig> characters;
    private  ArrayList<ElementConfig> items;
    private ArrayList<ElementConfig> exit;
    private ArrayList<String> map;
    private ArrayList<ControlConfig> controls;
    private ArrayList<ElementConfig> block;
    private int [] map_size;

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
    public ArrayList<ElementConfig> getExit(){
        return exit;
    }
    public ArrayList<ElementConfig> getBlock(){
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


    public static class ControlConfig {
        public String getName() {
            return name;
        }

        public Set<String> getInputKeys() {
            return input;
        }

        String  name;
        Set<String> input;
    }

    public static class ElementConfig {
        String name;
        char symbol;
        int [][] positions;
        String classname;

        public String getName() {
            return name;
        }

        public char getSymbol() {
            return symbol;
        }

        public int[][] getPositions() {
            return positions;
        }

        public String getClassname() {
            return this.classname;
        }

        @Override
        public String toString() {
            return "TokenConfig{" +
                    "name='" + name + '\'' +
                    ", symbol=" + symbol +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "GameConfig{" +
                "title='" + title + '\'' +
                ", title_art='" + title_art + '\'' +
                ", characters=" + characters +
                ", items=" + items +
                ", map=" + map +
                '}';
    }
}
