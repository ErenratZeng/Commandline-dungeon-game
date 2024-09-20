package Game.Model.Item;

import Engine.Engine;
import Engine.Model.Item;
import Game.Model.State.Inventory;

public class Sword extends Item {
    boolean isUsed;

    public Sword(int x, int y) {
        super(x, y, 'S');
        this.name = "Sword";
        this.isUsed = false;
    }

    @Override
    public void effect(Engine engine) {
        //TODO
        //By using sword, player can defeat an enemy without losing health
        isUsed = true;
    }

    @Override
    public void onInteract(Engine engine) {
        System.out.println("Yay! You found a sword (You can defeat one enemy without losing health) !");
        System.out.println("The sword has been added to you inventory !");
        Inventory inventory = (Inventory) engine.getState(Inventory.class.getName());
        inventory.addItem(this);
    }

    public boolean isUsed() {
        return isUsed;
    }

    @Override
    public boolean isBlocking() {
        return super.isBlocking();
    }

    public void setUsed() {
        isUsed = true;
    }

}
