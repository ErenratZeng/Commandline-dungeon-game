package Game;

import Engine.Engine;
import Engine.Model.Direction;
import Game.Model.Character.Enemy;
import Game.Model.Character.NPC;
import Game.Model.Character.Player;
import Game.Model.Item.GoldenApple;
import Game.Model.Item.Sword;
import Game.Model.State.Inventory;
import Game.Model.State.PlayerHealth;
import com.google.gson.JsonSyntaxException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

public class ItemsTest {

    private Engine engine;
    private Player player;

    @BeforeEach
    void setUp() throws FileNotFoundException, JsonSyntaxException {
        engine = new Engine("src/test/java/Game/TestConfig.json");
        engine.setCurrentMaze("testMaze");
        player = engine.getCharacter(Player.class).get(0);
    }

    @Test
    void PickUpItem() {
        Inventory inventory = engine.getState(Inventory.class);
        assertEquals(inventory.getValue().size(), 0, "The Inventory should be empty at game start");
        engine.moveCharacter(player, Direction.UP);
        engine.moveCharacter(player, Direction.RIGHT);
        assertEquals(inventory.getValue().size(), 2, "The Inventory should have 2 items");
        assertNotNull(inventory.getItem(Sword.class), "The Inventory should contain a sword");
        assertNotNull(inventory.getItem(GoldenApple.class), "The Inventory should contain a golden apple");
    }

    @Test
    void useGoldenApple() {
        PlayerHealth health = engine.getState(PlayerHealth.class);
        Inventory inventory = engine.getState(Inventory.class);
        int initialHealth = 50;
        health.setValue(initialHealth);
        engine.moveCharacter(player, Direction.UP);
        GoldenApple apple = inventory.getItem(GoldenApple.class);
        apple.effect(engine);
        int finalHealth = health.getValue();
        assertEquals(
                GoldenApple.HealCapacity,
                finalHealth - initialHealth,
                "The player health should increase by item heal %d".formatted(GoldenApple.HealCapacity)
        );
    }

    @Test
    void useSword() {
        PlayerHealth health = engine.getState(PlayerHealth.class);
        int initialHealth = health.getValue();
        engine.moveCharacter(player, Direction.RIGHT);
        engine.moveCharacter(player, Direction.UP);
        engine.moveCharacter(player, Direction.DOWN);
        engine.moveCharacter(player, Direction.RIGHT);
        engine.moveCharacter(player, Direction.RIGHT);
        int finalHealth = health.getValue();
        assertEquals(
                0,
                initialHealth - finalHealth,
                "The player health shouldn't decrease on enemy encounter"
        );
    }


}
