package Model;

import java.util.ArrayList;

public class Player extends Character{
    private int x;
    private int y;
    ArrayList inventory;
    public Player(int health) {
        super(health);
        inventory = new ArrayList();
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
