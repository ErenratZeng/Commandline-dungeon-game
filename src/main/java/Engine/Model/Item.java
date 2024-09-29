package Engine.Model;

import Engine.Engine;

/**
 * Abstract class representing an item in the maze. Items are a type of {@link MazeElement} that can interact with the player
 * or affect the game state when used. Each item has a position, symbol, and name, and can have custom effects and interactions.
 * @author Christo Antony
 * @author Qiutong Zeng
 */
public abstract class Item extends MazeElement {
    protected String name;

    /**
     * Constructs a new item with the specified position, symbol, and name.
     *
     * @param x    the x-coordinate of the item in the maze.
     * @param y    the y-coordinate of the item in the maze.
     * @param c    the character symbol representing the item.
     * @param name the name of the item.
     */
    public Item(int x, int y, char c, String name) {
        super(x, y, c, name);
    }

    /**
     * Gets the name of the item.
     *
     * @return the name of the item.
     */
    public String getName() {
        return name;
    }

    /**
     * Indicates whether this item blocks movement. By default, items do not block movement.
     *
     * @return false, as items do not block movement.
     */
    public boolean isBlocking() {
        return false;
    }

    /**
     * Abstract method to apply the item's effect. This method is called when the item is used, and the specific effect
     * must be defined by subclasses.
     *
     * @param engine the game engine, used to modify the game state based on the item's effect.
     */
    public abstract void effect(Engine engine);

    /**
     * Abstract method to define the interaction behavior when the player interacts with the item in the maze.
     * The interaction effect must be defined by subclasses.
     *
     * @param engine the game engine, used to handle the interaction.
     */
    public abstract void onInteract(Engine engine);
}
