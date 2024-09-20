package Game.Model.State;

import Engine.Model.GameState;

public class PlayerHealth extends GameState<Integer> {


    public PlayerHealth() {
        super(100);
    }

    public void reduceBy (Integer x) {
        this.value = Integer.max(0, this.value -x);
    }

    public void increaseBy (Integer x) {
        this.value = Integer.min(100, this.value + x);
    }

    @Override
    public void printGameState() {
        System.out.format("Health: %d", this.value);
    }
}
