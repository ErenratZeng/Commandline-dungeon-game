package Engine;

import Engine.Model.MazeElement;
import Engine.Model.Transition;
import Game.Model.Character.Enemy;
import Game.Model.Character.Player;
import Game.Model.Item.Rock;
import Game.Model.State.GameLevel;
import Game.Model.State.Inventory;
import Game.Model.State.PlayerHealth;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GameConfigTest {
    private Gson gson;
    private GameConfig config;
    @BeforeEach
    void setUp() throws FileNotFoundException, JsonSyntaxException {
        gson = new Gson();
        config = gson.fromJson(new FileReader("src/main/java/Game/GameConfig.json"), GameConfig.class);
    }

    @Test
    void title () {
        assertNotNull(config.getTitle());
    }

    @Test
    void elements () {
        assertNotNull(config.getElements());
        /* Ensure that all the classes defined are an instance or child of the MazeElement*/
        assertTrue(
                config.getElements().values().stream().allMatch((classname) -> {
                    try {
                        Class<?> aClass = Class.forName(classname);
                        if (!MazeElement.class.isAssignableFrom(aClass)) return  false;
                    } catch (ClassNotFoundException e) {
                        return false;
                    }
                    return true;
                }),
                "Not all elements in configuration is of MazeElement class"
        );
    }

    @Test
    void controls () {
        assertNotNull(config.getControls());
        /* build a hash map to ensure that no two controls are mapped to the same command */
        HashMap<String, String> map = new HashMap<>();
        for (var entry : config.getControls()) {
            for (String control: entry.input) {
                assertNull(map.get(control));
                map.put(control, entry.name);
            }
        }

    }

    @Test
    void uniformMapSize () {
        assertNotNull(config.getMazes());
        for (var map : config.getMazes().values()) {
            int row = map.length;
            int col = map[0].length;
            for (int i=1; i<row; i++) {
                assertEquals(col, map[i].length);
            }
        }
    }
    @Test
    void validMapCharacters () {
        assertNotNull(config.getMazes());
        HashSet<Character> characters = new HashSet<>(config.getElements().keySet());
        characters.add(config.getEmptyElement());
        for (var map : config.getMazes().values()) {
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[0].length; j++) {
                    Character c = map[i][j];
                    assertTrue(characters.contains(c), "The character "+ c + " is not valid");
                }
            }
        }
    }



}
