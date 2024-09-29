package Game.Model.Transition;

import Engine.Engine;
import Engine.Model.GameState;
import Engine.Model.Transition;
import Game.Model.State.GameWinState;

/**
 * The Exit class is concerned with the behaviour of the exit of every maze.
 *
 * Written in full by Christo Antony.
 */
public class Exit extends Transition {
    /**
     * Constructor for Exit class.
     *
     * @param x Horizontal position of the exit.
     * @param y Vertical coordinate of the exit.
     */
    public Exit(int x, int y) {
        super(x, y, 'Q', "Exit");
    }

    /**
     * Upon the player reaching the exit, this method updates the state of the
     * game to a "WIN".
     *
     * @param engine The config. JSON file that prints the
     *               game on the terminal.
     */
    @Override
    public void applyTransition(Engine engine) {
        System.out.println("You found the exit! Escape is now within reach!");
        GameWinState gameWinState = (GameWinState) engine.getState(GameWinState.class.getName());
        gameWinState.playerWin();
    }
}
