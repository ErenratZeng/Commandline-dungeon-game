package Game.Model.Item;

import Engine.Engine;
import Engine.Model.Item;
import Game.Model.State.Inventory;
import Game.Model.State.PlayerHealth;

/**
 * A class which governs the golden apple item functionality.
 *
 * Written by Christo Antony.
 */
public class GoldenApple extends Item {

    /**
     * Class constructor.
     *
     * @param x horizontal position of the instance.
     * @param y vertical position.
     */
    public GoldenApple(int x, int y) {
        super(x, y, 'G', "Golden Apple");
        this.name = "GoldenApple";
    }

    /**
     * Applies the effect of the item - heal the player by 10 points.
     *
     * @param engine The config. JSON file to print the game from.
     */
    @Override
    public void effect(Engine engine) {
        PlayerHealth health = (PlayerHealth) engine.getState(PlayerHealth.class.getName());
        Inventory inventory = (Inventory) engine.getState(PlayerHealth.class.getName());
        health.increaseBy(10);
        inventory.removeItem(this);
    }

    /**
     * Prints out text upon interacting with it in the environment and stores
     * it in the player's inventory.
     *
     * @param engine The config. JSON file.
     */
    @Override
    public void onInteract(Engine engine) {
        System.out.println("Consider the doctor's advice with this golden apple. (Re)gain +5 HP.");
        System.out.println("The apple is now in your inventory and awaiting consumption.");
        Inventory inventory = (Inventory) engine.getState(Inventory.class.getName());
        inventory.addItem(this);
    }

    /**
     * Return whether this subclass of items is a physical obstacle or not.
     *
     * @return False boolean.
     */
    @Override
    public boolean isBlocking() {
        return super.isBlocking();
    }
}
