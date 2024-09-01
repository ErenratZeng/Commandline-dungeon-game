package Controller;

import Model.Map;
public class Game {
    public static void main(String[] args) {
        Map map = new Map();
        map.load_map("src/MapDir/map1.json");
        map.printMap();

    }
}
