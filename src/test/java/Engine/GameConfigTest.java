package Engine;

import Engine.Model.MazeElement;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test suite for the GameConfig class.
 * It covers validation of the game configuration, ensuring that the title, elements, controls,
 * and mazes in the configuration file are properly set up and conform to the expected structure.
 * @author Christo Joby Antony
 */
public class GameConfigTest {

    private GameConfig config;

    /**
     * Sets up the test environment before each test is executed.
     * Loads the game configuration from the specified JSON file using Gson.
     *
     * @throws FileNotFoundException    if the configuration file is not found.
     * @throws JsonSyntaxException      if the configuration file contains invalid JSON.
     */
    @BeforeEach
    void setUp() throws FileNotFoundException, JsonSyntaxException {
        Gson gson = new Gson();
        config = gson.fromJson(new FileReader("src/main/java/Game/GameConfig.json"), GameConfig.class);
    }

    /**
     * Tests if the game title is loaded correctly from the configuration file.
     * Ensures the title is not null.
     */
    @Test
    void title() {
        assertNotNull(config.getTitle());
    }

    /**
     * Tests if the game elements are loaded correctly from the configuration file.
     * Ensures that the elements are not null and that all defined classes are instances or
     * subclasses of MazeElement.
     */
    @Test
    void elements() {
        assertNotNull(config.getElements());
        /* Ensure that all the classes defined are an instance or child of the MazeElement */
        assertTrue(
                config.getElements().values().stream().allMatch((classname) -> {
                    try {
                        Class<?> aClass = Class.forName(classname);
                        if (!MazeElement.class.isAssignableFrom(aClass)) return false;
                    } catch (ClassNotFoundException e) {
                        return false;
                    }
                    return true;
                }),
                "Not all elements in configuration are of MazeElement class"
        );
    }

    /**
     * Tests if the controls are loaded correctly from the configuration file.
     * Ensures that there are no duplicate controls mapped to the same command.
     * A hash map is built to check for duplicates.
     */
    @Test
    void controls() {
        assertNotNull(config.getControls());
        /* Build a hash map to ensure that no two controls are mapped to the same command */
        HashMap<String, String> map = new HashMap<>();
        for (var entry : config.getControls()) {
            for (String control : entry.input) {
                assertNull(map.get(control));
                map.put(control, entry.name);
            }
        }
    }

    /**
     * Tests if all maps defined in the configuration file have uniform row and column sizes.
     * Ensures that each row in a maze has the same number of columns.
     */
    @Test
    void uniformMapSize() {
        assertNotNull(config.getMazes());
        for (var map : config.getMazes().values()) {
            int row = map.length;
            int col = map[0].length;
            for (int i = 1; i < row; i++) {
                assertEquals(col, map[i].length);
            }
        }
    }

    /**
     * Tests if all characters used in the maze maps are valid.
     * Ensures that the characters in the maze correspond to the defined elements or the empty element.
     */
    @Test
    void validMapCharacters() {
        assertNotNull(config.getMazes());
        HashSet<Character> characters = new HashSet<>(config.getElements().keySet());
        characters.add(config.getEmptyElement());
        for (var map : config.getMazes().values()) {
            for (char[] chars : map) {
                for (int j = 0; j < map[0].length; j++) {
                    Character c = chars[j];
                    assertTrue(characters.contains(c), "The character " + c + " is not valid");
                }
            }
        }
    }
}
