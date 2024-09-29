package Game.Model.State;

import Engine.Model.GameState;
import Game.Game;

/**
 * A simple class that determines whether a playthrough is ongoing, won or
 * lost.
 *
 * @author Christo Antony.
 */
public class GameWinState extends GameState<GameWinState.WinState> {

    /**
     * Implements the three independent and possible states of a playthrough.
     */
    public enum WinState {
        PLAYER_WIN,
        PLAYER_LOSE,
        ONGOING,
    }

    /**
     * Constructor for the class which initialises a new playthrough to an
     * "ONGOING" state.
     */
    public GameWinState() {
        super(WinState.ONGOING);
    }

    /**
     * Provided the appropriate conditions for the method call, set the game
     * instance to "WIN".
     */
    public void playerWin() {
        this.value = WinState.PLAYER_WIN;
    }

    /**
     * Set the state of the game to "LOSE".
     */
    public void playerLose()  {
        this.value = WinState.PLAYER_LOSE;
    }
}
