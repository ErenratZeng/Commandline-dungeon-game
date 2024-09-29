package Engine.Model;

/**
 * Abstract class representing the state of a game. A `GameState` holds a value of type `T`, which can be any type of data
 * representing part of the game's current state (e.g., player health, score, or level progress). This class provides
 * getter and setter methods to access and modify the game state.
 *
 * @author Christo Antony
 *
 * @param <T> the type of value this game state holds.
 */
public abstract class GameState<T> {
    protected T value; // The current value representing the state

    /**
     * Constructs a new `GameState` with an initial value.
     *
     * @param initialValue the initial value of the game state.
     */
    public GameState(T initialValue) {
        this.value = initialValue;
    }

    /**
     * Gets the current value of the game state.
     *
     * @return the current value of type `T`.
     */
    public T getValue() {
        return value;
    }

    /**
     * Sets the value of the game state.
     *
     * @param value the new value of the game state.
     */
    public void setValue(T value) {
        this.value = value;
    }

    /**
     * Prints the current value of the game state to the console.
     */
    public void print() {
        System.out.println(this.value);
    }

}
