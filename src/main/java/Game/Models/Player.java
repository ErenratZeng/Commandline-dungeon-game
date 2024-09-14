package Game.Models;

import Engine.Model.Character;
import Engine.Model.Direction;
import Engine.Model.Item;

import java.util.ArrayList;

public class Player extends Character {
    ArrayList inventory;
    public Player(int x, int y, char c) {
        super(x, y, c);
        inventory = new ArrayList<Item>();
    }

    public int[] movePlayer(Direction direction){
        int newX = this.getX();
        int newY = this.getY();

        switch (direction){
            case UP:
                newX -= 1;
                break;
            case DOWN:
                newX += 1;
                break;
            case LEFT:
                newY -= 1;
                break;
            case RIGHT:
                newY += 1;
                break;
        }
        return new int[]{newX, newY};
    }

    @Override
    public int getHealth() {
        return super.getHealth();
    }

    public int heal(int amount){
        return getHealth() + amount;
    }

    //When player pick up item, the effect will be applied
    public void use_item(Item item){
        //TODO
    }

}
