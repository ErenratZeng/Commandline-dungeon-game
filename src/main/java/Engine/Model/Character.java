package Engine.Model;

import Engine.Engine;

/**
 * Abstract class representing a character in the maze. A `Character` is a type of {@link MazeElement} that can move
 * and interact with other elements in the maze. Characters have a position, symbol, and name, and can define specific
 * behaviors for movement and interaction.
 * @author Christo Antony
 */
public abstract class Character extends MazeElement {

    private char symbol;
    private String description;

    /**
     * Constructs a new character with the specified position, symbol, and name.
     *
     * @param x    the x-coordinate of the character in the maze.
     * @param y    the y-coordinate of the character in the maze.
     * @param c    the character symbol representing the character.
     * @param name the name of the character.
     */
    public Character(int x, int y, char c, String name){
        super(x, y, c, name);
    }

    /**
     * Sets the position of the character in the maze.
     *
     * @param x the new x-coordinate of the character.
     * @param y the new y-coordinate of the character.
     */
    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
    }

    /**
     * Abstract method to move the character in the specified direction.
     * This method must be implemented by subclasses to define how the character moves within the maze.
     *
     * @param direction the direction in which the character should move.
     * @return an array of two integers representing the new x and y coordinates of the character.
     */
    public abstract int[] moveCharacter(Direction direction);

    /**
     * Abstract method to handle interaction with the character. This method is called when another element interacts
     * with the character, and the specific behavior must be defined by subclasses.
     *
     * @param engine the game engine, used to handle the interaction.
     * @return true if the interaction allows further movement or actions, false if the interaction blocks movement.
     */
    public abstract boolean onInteract (Engine engine);
}
