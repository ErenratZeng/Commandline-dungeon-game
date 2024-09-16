package Game.Models;

import Engine.Model.Item;
import Game.Models.Player;

public class Sword extends Item {
    boolean isUsed;
    public Sword(int x, int y, char c) {
        super(x, y, c);
        this.isUsed = false;
    }

    @Override
    public void effect(Player player) {
        //TODO
        //By using sword, player can defeat enemy without losing health
    }

    public boolean isUsed(){
        return isUsed;
    }

    @Override
    public boolean isBlocking() {
        return super.isBlocking();
    }

    public void setUsed(){
        isUsed = true;
    }

}
