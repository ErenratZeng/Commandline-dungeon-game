package Game.Model.Item;

import Engine.Engine;
import Engine.Model.Item;
import Game.Model.State.Inventory;

public class Sword extends Item {

    public Sword(int x, int y) {
        super(x, y, 'S', "Sword");
        this.name = "Sword";
    }

    @Override
    public void effect(Engine engine) {
        //Player can defeat a single enemy without losing health
        System.out.println("The sword will protect you against the next enemy attack");
    }

    @Override
    public void onInteract(Engine engine) {
        System.out.println("Yay! You found a sword (You can defeat one enemy without losing health) !");
        System.out.println("The sword has been added to you inventory !");
        Inventory inventory = engine.getState(Inventory.class);
        inventory.addItem(this);
    }

    @Override
    public boolean isBlocking() {
        return super.isBlocking();
    }

}
