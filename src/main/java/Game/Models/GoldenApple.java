package Game.Models;

import Engine.Model.Item;
import Game.Models.Player;

public class GoldenApple extends Item {
    public GoldenApple(int x, int y, char c) {
        super(x, y, c);
    }

    @Override
    public void effect(Player player) {
        //TODO
        //Heal player for 1 health
    }
}
