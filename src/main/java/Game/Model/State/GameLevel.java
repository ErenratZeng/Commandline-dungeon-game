package Game.Model.State;

import Engine.Model.GameState;

public class GameLevel extends GameState<Integer> {

    public GameLevel() {
        super(1);
    }

    public void nextLevel() {
        this.value ++;
    }

}
