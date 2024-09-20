package Engine.Model;

import Engine.Engine;

public abstract class Character extends MazeElement {

    private char symbol;
    private String description;

    public Character(int x, int y, char c, String name){
        super(x, y, c, name);
    }
    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
    }

    public abstract int[] moveCharacter(Direction direction);

    public abstract boolean onInteract (Engine engine);
}
