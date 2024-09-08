package Engine.Model;

import Game.Models.Player;

public abstract class Item extends MazeElement {
    protected String name;

    public Item(int x, int y,  char c) {
        super(x, y, c);
    }

    public String getName() {
        return name;
    }

    public abstract void effect(Player player);
}
