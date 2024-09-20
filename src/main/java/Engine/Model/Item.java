package Engine.Model;

import Engine.Engine;

public abstract class Item extends MazeElement {
    protected String name;

    public Item(int x, int y,  char c) {
        super(x, y, c);
    }

    public String getName() {
        return name;
    }

    public boolean isBlocking() {
        return false;
    }

    public abstract void effect(Engine engine);

    public abstract void onInteract(Engine engine);
}
