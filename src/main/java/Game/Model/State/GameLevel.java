package Game.Model.State;

import Engine.Model.GameState;

import java.util.concurrent.Callable;

public class GameLevel extends GameState<Integer> {

    Runnable onLevelChange;
    public GameLevel() {
        super(1);
    }

    public void nextLevel() {
        this.value ++;
        if (onLevelChange != null) onLevelChange.run();
    }
    public void onLevelChange (Runnable onLevelChange) {
        this.onLevelChange = onLevelChange;
    }

}
