package Game.Models;

import Engine.Model.Character;
import Engine.Model.Item;

import java.util.ArrayList;

public class Player extends Character {
    private int x;
    private int y;
    ArrayList inventory;
    public Player(int x, int y, char c) {
        super(x, y, c);
        inventory = new ArrayList<Item>();
    }

    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int[] getPosition(){
        return new int[]{x, y};
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
