package Game.Model.State;

import Engine.Model.GameState;

/**
 * The class governs the player's health and all code that alters it through
 * various interactions during gameplay.
 *
 * @author Christo Antony.
 */
public class PlayerHealth extends GameState<Integer> {

    /**
     * Parameterless constructor to initialise the health of the player on
     * startup of a new playthrough.
     */
    public PlayerHealth() {
        super(100);
    }

    /**
     * Call on this method to reduce an instance of PlayerHealth by a given
     * amount.
     *
     * @param x The value with which to reduce the player's health by.
     */
    public void reduceBy(Integer x) {
        this.value = Integer.max(0, this.value -x);
    }

    /**
     * Given an instance of PlayerHealth, increase it by a specified amount.
     *
     * @param x The value to add on to a player's health.
     */
    public void increaseBy(Integer x) {
        this.value = Integer.min(100, this.value + x);
    }

    /**
     * Display on the terminal the current hitpoints left of the player.
     */
    @Override
    public void print() {
        System.out.format("Health: %d\n", this.value);
    }
}
