package Game;

import Engine.Engine;
import Game.Model.Item.GoldenApple;
import Game.Model.State.GameLevel;
import Game.Model.State.GameWinState;
import Game.Model.State.Inventory;
import Game.Model.State.PlayerHealth;
import com.google.gson.JsonSyntaxException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

public class GameStateTest {

    private Engine engine;

    /**
     * Sets up the test environment before each test is executed.
     * Loads the game engine with the test configuration and sets up the player character in the maze.
     *
     * @throws FileNotFoundException    if the configuration file is not found.
     * @throws JsonSyntaxException      if the configuration file contains invalid JSON.
     */
    @BeforeEach
    void setUp() throws FileNotFoundException, JsonSyntaxException {
        engine = new Engine("src/test/java/Game/TestConfig.json");
        engine.setCurrentMaze("testMaze");
    }

    @Test
    void PlayerHealthMax100() {
        PlayerHealth health = engine.getState(PlayerHealth.class);
        health.setValue(100);
        health.increaseBy(20);
        assertEquals(100, health.getValue(), "Player health cannot be greater than 100");
    }

    @Test
    void PlayerHealthMin0() {
        PlayerHealth health = engine.getState(PlayerHealth.class);
        health.setValue(10);
        health.reduceBy(50);
        assertEquals(0, health.getValue(), "Player health cannot be negative");
    }

    @Test
    void AddToInventory() {
        Inventory inventory = engine.getState(Inventory.class);
        assertEquals(0, inventory.getValue().size(), "The Inventory should be empty at Game Start");
        inventory.addItem(new GoldenApple(0, 0));
        assertEquals(1, inventory.getValue().size(), "The Inventory should now contain exactly one item");
        assertNotNull(inventory.getItem(GoldenApple.class), "The Inventory should now contain a Golden Apple");
    }

    @Test
    void GameWinState() {
        GameWinState winState = engine.getState(GameWinState.class);
        assertEquals(GameWinState.WinState.ONGOING, winState.getValue(), "Game should not be won at the start");
    }

    @Test
    void GameLevelState() {
        GameLevel levelState = engine.getState(GameLevel.class);
        assertEquals(1, levelState.getValue(), "Game should start at level 1");
        levelState.nextLevel();
        assertEquals(2, levelState.getValue(), "Game should be at level 2 after setting level state to 2");
    }
}
