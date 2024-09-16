package Game.Models;

import Engine.Model.Item;

public class Rock extends Item {
    public Rock(int x, int y, char c) {
        super(x, y, c);
    }

    @Override
    public boolean isBlocking() {
        return true;
    }

    @Override
    public void effect(Player player) {

    }
}
