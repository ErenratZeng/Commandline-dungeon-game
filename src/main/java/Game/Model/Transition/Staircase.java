package Game.Model.Transition;

import Engine.Engine;
import Engine.Model.Transition;
import Game.Model.State.GameLevel;

/**
 * The class deals with behaviour of the staircase "tile" that appears
 * at each level of every maze.
 *
 * Written in full by Christo Antony.
 */
public class Staircase extends Transition {

    /**
     * The set of currently-implemented maze level designations.
     */
    static final String[] maze_level = {"level_1", "level_2", "level_3"};

    /**
     * Constructor for the class.
     *
     * @param x x-coordinate of the staircase.
     * @param y y-coordinate.
     */
    public Staircase(int x, int y) {
        super(x, y, 'Z', "Staircase");
    }

    /**
     * After reaching the staircase of a level, update said level to the next
     * one and display the corresponding layout on the terminal.
     *
     * @param engine The config. JSON file for printing the game on the
     *               terminal.
     */
    @Override
    public void applyTransition(Engine engine) {
        GameLevel gameLevel = engine.getState(GameLevel.class);
        gameLevel.nextLevel();
        String mazeName = maze_level[gameLevel.getValue()-1];
        engine.setCurrentMaze(mazeName);
        System.out.println("You found a staircase! Hope briefly surges through you as you find your chances of survival increase.");
    }
}
