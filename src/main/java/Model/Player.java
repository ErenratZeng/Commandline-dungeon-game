package Model;

import java.util.ArrayList;

public class Player extends Character{
    ArrayList inventory;
    public Player(int health) {
        super(health);
        inventory = new ArrayList();
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
