package Game.Model.State;

import Engine.Model.GameState;
import Game.Game;

public class GameWinState extends GameState<GameWinState.WinState> {

    public enum WinState {
        PLAYER_WIN,
        PLAYER_LOSE,
        ONGOING,
    }
    public GameWinState() {
        super(WinState.ONGOING);
    }

    public void playerWin () {
        this.value = WinState.PLAYER_WIN;
    }

    public void playerLose ()  {
        this.value = WinState.PLAYER_LOSE;
    }

}
