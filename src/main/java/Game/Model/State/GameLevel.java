package Game.Model.State;

import Engine.Model.GameState;

import java.util.concurrent.Callable;

/**
 * A small class that is concerned with the current level of a maze.
 *
 * @author Christo Antony.
 */
public class GameLevel extends GameState<Integer> {

    /**
     * Constructor which defaults the maze to the first level for every
     * instantiation.
     */
    public GameLevel() {
        super(1);
    }

    /**
     * Sets the current level to the next level (+1).
     */
    public void nextLevel() {
        this.value++;
    }
}
