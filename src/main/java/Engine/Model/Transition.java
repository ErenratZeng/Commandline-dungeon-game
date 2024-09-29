package Engine.Model;

import Engine.Engine;

/**
 * Abstract class representing a transition element in the maze.
 * A transition is a type of {@link MazeElement} that, when interacted with, triggers a specific state change
 * or transition in the game (e.g., moving to a different maze, changing the game state).
 *
 * This class defines a method for applying a transition, which must be implemented by subclasses.
 *
 * @author Christo Antony
 */
public abstract class Transition  extends  MazeElement{

    /**
     * Constructs a new Transition with the specified position, symbol, and name.
     *
     * @param x     the x-coordinate of the transition in the maze.
     * @param y     the y-coordinate of the transition in the maze.
     * @param s     the character symbol representing the transition.
     * @param name  the name of the transition.
     */
    public  Transition(int x, int y, char s, String name) {
        super(x, y, s, name);
    }

    /**
     * Abstract method to apply the transition effect. This method is triggered when the player interacts with the transition.
     * Subclasses must provide their own implementation to define the specific behavior of the transition.
     *
     * @param engine the game engine, used to modify the game state based on the transition.
     */
    public  abstract void applyTransition(Engine engine);
}
