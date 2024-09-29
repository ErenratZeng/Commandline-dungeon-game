package Game.Model.Character;

import Engine.Engine;
import Engine.Model.Character;
import Engine.Model.Direction;

/**
 * The class for the player which concerns their functionality in the game.
 *
 * Written by Christo Antony.
 */
public class Player extends Character {

    /**
     * Constructor class. Instantiate their model in-game and position.
     *
     * @param x x-coordinate of the player.
     * @param y y-coordinate of the player.
     */
    public Player(int x, int y) {
        super(x, y, 'P', "Player");
    }

    /**
     * Update the coordinates of the player's model based on player input.
     *
     * @param direction Enum values representing movement.
     * @return A list of the new coordinates.
     */
    @Override
    public int[] moveCharacter(Direction direction){
        int newX = this.x;
        int newY = this.y;

        switch (direction){
            case UP:
                newY -= 1;
                break;
            case DOWN:
                newY += 1;
                break;
            case LEFT:
                newX -= 1;
                break;
            case RIGHT:
                newX += 1;
                break;
        }
        return new int[]{newX, newY};
    }

    /**
     * Handles the case where the player is ever able to interact with
     * themself.
     *
     * @param engine The config. JSON file to print the game from.
     * @return False boolean and an error message.
     */
    @Override
    public boolean onInteract(Engine engine) {
        System.err.println("What is this? Faster than you could recognise, malice of a higher power had" +
                " suddenly made itself known to you. Quick in all its actions, it pushes against" +
                " and through your psyche to bring into being a fleeting shadow before you." +
                " A shadow of you, unmasked to reveal your evil. A message, disguised as a premonition;" +
                " be wary of this world.");
        System.err.println("As it vanishes in a haze of black, you take its warning to heart before proceeding again.");
        return false;
    }
}
