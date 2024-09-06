package Engine;

import java.util.ArrayList;

public class GameConfig {
    private String title;
    private String title_art;
    private ArrayList<ElementConfig> characters;

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

    public ArrayList<String> getMap() {
        return map;
    }

    private  ArrayList<ElementConfig> items;
    private ArrayList<String> map;

    public int[] getMapSize() {
        return map_size;
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
