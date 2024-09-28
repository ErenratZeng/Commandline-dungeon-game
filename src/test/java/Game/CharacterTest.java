package Game;

import Engine.Engine;
import Engine.Model.Direction;
import Game.Model.Character.Enemy;
import Game.Model.Character.NPC;
import Game.Model.Character.Player;
import Game.Model.State.PlayerHealth;
import com.google.gson.JsonSyntaxException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test suite for character interactions in the game, including interactions between the player,
 * enemies, and non-player characters (NPCs).
 * It tests how the player's health is affected by these interactions.
 *
 * @author Christo Joby Antony
 */
public class CharacterTest {

    private Engine engine;
    private Player player;

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
        player = engine.getCharacter(Player.class).get(0);
    }

    /**
     * Tests the interaction between the player and an enemy character.
     * Ensures that the player's health decreases by the correct amount after an enemy encounter.
     */
    @Test
    void EnemyInteraction() {
        PlayerHealth health = engine.getState(PlayerHealth.class);
        int initialHealth = health.getValue();
        int damage = engine.getCharacter(Enemy.class).get(0).getDamage();
        engine.moveCharacter(player, Direction.RIGHT);
        engine.moveCharacter(player, Direction.RIGHT);
        engine.moveCharacter(player, Direction.RIGHT);
        int finalHealth = health.getValue();
        assertEquals(damage, initialHealth - finalHealth, "The player health should decrease by enemy damage %d".formatted(damage));
    }

    /**
     * Tests the interaction between the player and an NPC.
     * Ensures that the player's health increases by the defined heal amount after encountering the NPC.
     */
    @Test
    void NPCInteraction() {
        PlayerHealth health = engine.getState(PlayerHealth.class);
        int heal = NPC.HEAL_AMOUNT;
        int initialHealth = 50;
        health.setValue(initialHealth);
        engine.moveCharacter(player, Direction.RIGHT);
        engine.moveCharacter(player, Direction.RIGHT);
        engine.moveCharacter(player, Direction.UP);
        int finalHealth = health.getValue();
        assertEquals(initialHealth + heal, finalHealth, "The player health should increase by %d due to NPC".formatted(heal));
    }
}

