package Game.Model.State;

import Engine.Model.GameState;

import java.util.concurrent.Callable;

public class GameLevel extends GameState<Integer> {

    public GameLevel() {
        super(1);
    }

    public void nextLevel() {
        this.value ++;
    }

}
