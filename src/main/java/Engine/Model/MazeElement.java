package Engine.Model;

/**
 * Abstract class representing an element in the maze.
 * A `MazeElement` can be any object that occupies a position within the maze, such as a player, an item, or a transition.
 * Each element has a position (x, y), a symbol that represents it visually, and a name.
 *
 * @author Christo Antony
 * @author Qiutong Zeng
 */
public abstract class MazeElement {
    protected int x; // The x-coordinate of the element in the maze.
    protected int y; // The y-coordinate of the element in the maze.
    protected char symbol; // The character symbol representing the element.
    protected String name; // The name of the element.

    /**
     * Gets the symbol representing this maze element.
     *
     * @return the character symbol of the maze element.
     */
    public char getSymbol() {
        return symbol;
    }

    /**
     * Gets the name of this maze element.
     *
     * @return the name of the maze element.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the name of this maze element.
     *
     * @return the name of the maze element.
     */
    MazeElement(int x, int y, char s, String name
    ) {
        this.x = x;
        this.y = y;
        this.symbol = s;
        this.name = name;
    }

    /**
     * Determines if this maze element blocks movement.
     * By default, all maze elements block movement. Subclasses can override this method to change this behavior.
     *
     * @return true if the element blocks movement, false otherwise.
     */
    public boolean isBlocking() {
        return true;
    }

    /**
     * Gets the y-coordinate of this maze element.
     *
     * @return the y-coordinate of the maze element.
     */
    public int getY() {
        return y;
    }

    /**
     * Gets the x-coordinate of this maze element.
     *
     * @return the x-coordinate of the maze element.
     */
    public int getX() {
        return x;
    }
}
