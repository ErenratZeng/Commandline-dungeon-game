package Game.Model.Item;

import Engine.Engine;
import Engine.Model.Item;
import Game.Model.State.Inventory;

/**
 * An Item class which deals with interactions with enemies should instances
 * exist in the player's inventory.
 *
 * Written by Christo Antony.
 */
public class Sword extends Item {

    /**
     * Class constructor. Initialise the position of an instance.
     *
     * @param x Position in the x-direction.
     * @param y Position in the y-direction.
     */
    public Sword(int x, int y) {
        super(x, y, 'S', "Sword");
        this.name = "Sword";
    }

    /**
     * Display the effect of the item.
     * The player can defeat a single enemy without losing any health.
     *
     * @param engine The config. JSON file for printing the game on the
     *               terminal.
     */
    @Override
    public void effect(Engine engine) {
        System.out.println("Frail by age but nevertheless guarded by mystical energies of its previous owner," +
                " this sword will protect you against the next enemy attack.");
    }

    /**
     * On interaction with a sword item in a level, update the inventory with
     * the addition of a sword and print out corresponding text.
     *
     * @param engine Config. JSON file used for printing the game.
     */
    @Override
    public void onInteract(Engine engine) {
        System.out.println("You found an... odd sword. Decrepit, yet vibrating with unknown power.");
        System.out.println("Carefully, you add the sword to your inventory.");
        Inventory inventory = engine.getState(Inventory.class);
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
