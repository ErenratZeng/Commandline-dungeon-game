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

public class CharacterTest {

    private Engine engine;
    private Player player;
    @BeforeEach
    void setUp() throws FileNotFoundException, JsonSyntaxException {
        engine = new Engine("src/test/java/Game/TestConfig.json");
        engine.setCurrentMaze("testMaze");
        player = engine.getCharacter(Player.class).get(0);
    }

    @Test
    void EnemyInteraction () {
        PlayerHealth health = engine.getState(PlayerHealth.class);
        int initialHealth = health.getValue();
        int damage = engine.getCharacter(Enemy.class).get(0).getDamage();
        engine.moveCharacter(player, Direction.RIGHT);
        engine.moveCharacter(player, Direction.RIGHT);
        engine.moveCharacter(player, Direction.RIGHT);
        int finalHealth = health.getValue();
        assertEquals( damage, initialHealth - finalHealth, "The player health should decrease by enemy damage %d".formatted(damage));
    }
    @Test
    void NPCInteraction () {
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
