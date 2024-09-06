package Game;

import Game.Models.Player;
import Engine.Engine;

import java.io.FileNotFoundException;

public class Game {
    public static void main(String[] args) {
        Engine engine = null;
        try {
            engine = new Engine("src/main/java/Game/GameConfig.json");
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException(e);
        }
        engine.printMap();

    }
}
