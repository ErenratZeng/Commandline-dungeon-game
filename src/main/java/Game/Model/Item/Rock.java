package Game.Model.Item;

import Engine.Engine;
import Engine.Model.Item;

/**
 * The class dealing with the Rock environmental entity.
 *
 * @author Christo Antony.
 */
public class Rock extends Item {

    /**
     * Constructor class, initialising its appearance in-game as well.
     *
     * @param x x-value of the rock's position.
     * @param y y-value of its position.
     */
    public Rock(int x, int y) {
        super(x, y, '#', "Rock");
        this.name = "Rock";
    }

    /**
     * Return whether this subclass of items is a physical obstacle or not.
     *
     * @return False boolean.
     */
    @Override
    public boolean isBlocking() {
        return true;
    }

    /**
     * Display the appropriate text when interacting with this entity.
     *
     * @param engine The config. JSON file for printing the game on the terminal.
     */
    @Override
    public void onInteract(Engine engine) {
        System.out.println("Something is watching this rock from the shadows." +
                           " It would be best to not incur its ire.");
    }

    /**
     * In the event that it ever occurs, display text when it is used
     * in the inventory.
     *
     * @param engine The config. JSON file.
     */
    @Override
    public void effect(Engine engine) {
        System.out.println("The watcher of this rock is growing restless." +
                           " Do you want to hasten your demise?");
    }
}
