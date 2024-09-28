package Engine;

import Engine.Model.*;
import Game.Model.Character.Enemy;
import Game.Model.Character.NPC;
import Game.Model.Character.Player;
import Game.Model.Item.Rock;
import Game.Model.State.GameLevel;
import Game.Model.State.Inventory;
import Game.Model.State.PlayerHealth;
import com.google.gson.JsonSyntaxException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test suite for the Engine class.
 * It covers multiple functionalities such as loading configuration, validating characters,
 * items, transitions, player health, game states, and testing different types of player movements.
 * The tests ensure that the Engine's core functionalities behave as expected.
 *
 * @author Christo Joby Antony
 */
class EngineTest {

    private Engine engine;

    /**
     * Sets up the test environment before each test is executed.
     * Initializes the Engine instance using a test configuration file and sets the current maze.
     *
     * @throws FileNotFoundException if the configuration file is not found.
     * @throws JsonSyntaxException   if the configuration file contains invalid JSON.
     */
    @BeforeEach
    void setUp() throws FileNotFoundException, JsonSyntaxException {
        engine = new Engine("src/test/java/Game/TestConfig.json");
        engine.setCurrentMaze("testMaze");
    }

    /**
     * Tests if the configuration can be loaded without any exceptions.
     */
    @Test
    void loadConfig() {
        assertDoesNotThrow(() -> new Engine("src/test/java/Game/TestConfig.json"));
    }

    /**
     * Validates the characters loaded by the Engine.
     * Ensures the expected number of players and enemies are correctly initialized.
     */
    @Test
    void validateCharacter() {
        assertEquals(3, engine.characters.size());
        assertEquals(1, engine.getCharacter(Player.class).size());
        assertEquals(1, engine.getCharacter(Enemy.class).size());
    }

    /**
     * Validates the items loaded by the Engine.
     * Ensures that 13 rocks are loaded as expected.
     */
    @Test
    void validateItems() {
        assertEquals(3, engine.items.size());
        ArrayList<Rock> rocks = engine.items.get(Rock.class.getName())
                .stream()
                .filter(Rock.class::isInstance)
                .map(Rock.class::cast)
                .collect(Collectors.toCollection(ArrayList::new));
        assertEquals(13, rocks.size());
    }

    /**
     * Validates the transitions in the current maze.
     * Ensures there is exactly 1 transition element in the maze.
     */
    @Test
    void validateTransitions() {
        ArrayList<Transition> transitions = new ArrayList<>();
        for (int i = 0; i < engine.currentMaze.getRows(); i++) {
            for (int j = 0; j < engine.currentMaze.getCols(); j++) {
                MazeElement e = engine.currentMaze.getElement(j, i);
                if (e instanceof Transition transition) transitions.add(transition);
            }

        }
        assertEquals(transitions.size(), 1);
    }

    /**
     * Validates the player's health state is correctly initialized in the game.
     */
    @Test
    void validatePlayerHealthState() {
        PlayerHealth health = engine.getState(PlayerHealth.class);
        assertNotNull(health);
    }

    /**
     * Validates the various game states such as GameLevel, Inventory, and PlayerHealth.
     * Ensures they are correctly initialized and available.
     */
    @Test
    void validateGameStates() {
        assertEquals(engine.gameStates.values().size(), 4);
        GameLevel gameLevel = engine.getState(GameLevel.class);
        assertNotNull(gameLevel);
        Inventory inventory = engine.getState(Inventory.class);
        assertNotNull(inventory);
        PlayerHealth playerHealth = engine.getState(PlayerHealth.class);
        assertNotNull(playerHealth);
    }

    /**
     * Tests if the player character does not move out of bounds when attempting to move left.
     * Ensures the player's position remains valid and unchanged after attempting the move.
     */
    @Test
    void testOutOfBoundsMove() {
        Player player = engine.getCharacter(Player.class).get(0);
        engine.moveCharacter(player, Direction.LEFT);
        assertArrayEquals(new int[]{0, 1}, new int[]{player.getX(), player.getY()});
        assertEquals(engine.currentMaze.getElement(0, 1), player);
    }

    /**
     * Tests if the player character can successfully move into a location containing an item (Rock).
     * Ensures the player's position is correctly updated.
     */
    @Test
    void testMoveIntoItem() {
        Player player = engine.getCharacter(Player.class).get(0);
        engine.moveCharacter(player, Direction.DOWN);
        assertArrayEquals(new int[]{0, 1}, new int[]{player.getX(), player.getY()});
        assertEquals(engine.currentMaze.getElement(0, 1), player);
    }

    /**
     * Tests if the player character can successfully move into an empty space.
     * Ensures the player's position is correctly updated after the move.
     */
    @Test
    void testMoveIntoEmpty() {
        Player player = engine.getCharacter(Player.class).get(0);
        engine.moveCharacter(player, Direction.RIGHT);
        assertArrayEquals(new int[]{1, 1}, new int[]{player.getX(), player.getY()});
        assertEquals(player, engine.currentMaze.getElement(1, 1));
    }

    /**
     * Tests if the player character can successfully move into a space occupied by another character.
     * Ensures the player's position is correctly updated after the move.
     */
    @Test
    void TestMoveIntoCharacter() {
        Player player = engine.getCharacter(Player.class).get(0);
        engine.moveCharacter(player, Direction.RIGHT);
        engine.moveCharacter(player, Direction.RIGHT);
        engine.moveCharacter(player, Direction.RIGHT);
        assertArrayEquals(new int[]{3, 1}, new int[]{player.getX(), player.getY()});
    }

}
