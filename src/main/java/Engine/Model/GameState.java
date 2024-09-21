package Engine.Model;

public abstract class GameState<T> {

    public GameState(T initialValue) {
        this.value = initialValue;
    }

    protected T value;

    public T getValue() {
        return value;
    }
    public void setValue(T value) {
        this.value = value;
    }
    public void print() {
        System.out.println(this.value);
    }

}
