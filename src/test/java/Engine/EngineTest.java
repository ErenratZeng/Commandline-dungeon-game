package Engine;

import Engine.Model.*;
import Game.Model.Character.Enemy;
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
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class EngineTest {

    private Engine engine;

    @BeforeEach
    void setUp() throws FileNotFoundException, JsonSyntaxException {
        engine = new Engine("src/test/java/Engine/TestConfig.json");
        engine.setCurrentMaze("testMaze");
    }

    @Test
    void loadConfig() {
        assertDoesNotThrow(() -> new Engine("src/test/java/Engine/TestConfig.json"));
    }

    @Test
    void validateCharacter() {
        assertEquals(engine.characters.size(), 2);
        assertEquals(engine.getCharacter(Player.class).size(), 1);
        assertEquals(engine.getCharacter(Enemy.class).size(), 1);
    }

    @Test
    void validateItems() {
        assertEquals(engine.items.size(), 1);
        ArrayList<Rock> rocks = engine.items.get(Rock.class.getName())
                .stream()
                .filter(Rock.class::isInstance)
                .map(Rock.class::cast)
                .collect(Collectors.toCollection(ArrayList::new));
        assertEquals(rocks.size(), 13);
    }

    @Test
    void validateTransitions() {
        ArrayList<Transition> transitions = new ArrayList<>();
        for (int i = 0; i < engine.currentMaze.getRows(); i++) {
            for (int j = 0; j < engine.currentMaze.getRows(); j++) {
                MazeElement element = engine.currentMaze.getElement(j, i);
                if (element instanceof Transition transition) transitions.add(transition);
            }

        }
        assertEquals(transitions.size(), 1);
    }

    @Test
    void validatePlayerHealthState() {
        PlayerHealth health = engine.getState(PlayerHealth.class);
        assertNotNull(health);
    }

    @Test
    void validateGameStates() {
        assertEquals(engine.gameStates.values().size(), 3);
        GameLevel gameLevel = engine.getState(GameLevel.class);
        assertNotNull(gameLevel);
        Inventory inventory = engine.getState(Inventory.class);
        assertNotNull(inventory);
        PlayerHealth playerHealth = engine.getState(PlayerHealth.class);
        assertNotNull(playerHealth);
    }

    @Test
    void testOutOfBoundsMove() {
        Player player = engine.getCharacter(Player.class).get(0);
        engine.moveCharacter(player, Direction.LEFT);
        assertArrayEquals(new int[]{0, 1}, new int[]{player.getX(), player.getY()});
        assertEquals(engine.currentMaze.getElement(0, 1), player);
    }

    @Test
    void testMoveIntoItem() {
        Player player = engine.getCharacter(Player.class).get(0);
        engine.moveCharacter(player, Direction.DOWN);
        assertArrayEquals(new int[]{0, 1}, new int[]{player.getX(), player.getY()});
        assertEquals(engine.currentMaze.getElement(0, 1), player);
    }

    @Test
    void testMoveIntoEmpty() {
        Player player = engine.getCharacter(Player.class).get(0);
        engine.moveCharacter(player, Direction.RIGHT);
        assertArrayEquals(new int[]{1, 1}, new int[]{player.getX(), player.getY()});
        assertEquals(engine.currentMaze.getElement(1, 1), player);
    }

    @Test
    void TestMoveIntoCharacter() {
        Player player = engine.getCharacter(Player.class).get(0);
        engine.moveCharacter(player, Direction.RIGHT);
        engine.moveCharacter(player, Direction.RIGHT);
        engine.moveCharacter(player, Direction.RIGHT);
        assertArrayEquals(new int[]{3, 1}, new int[]{player.getX(), player.getY()});
    }


}