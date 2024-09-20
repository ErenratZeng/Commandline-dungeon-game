package Game.Model.Transition;

import Engine.Engine;
import Engine.Model.GameState;
import Engine.Model.Transition;
import Game.Model.State.GameWinState;

public class Exit extends Transition {
    public Exit(int x, int y) {
        super(x, y,'Q', "Exit");
    }

    @Override
    public void applyTransition(Engine engine) {
        System.out.println("Yay, you found the exit !!");
        GameWinState gameWinState = (GameWinState) engine.getState(GameWinState.class.getName());
        gameWinState.playerWin();
    }
}
