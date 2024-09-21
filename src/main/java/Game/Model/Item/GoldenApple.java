package Game.Model.Item;

import Engine.Engine;
import Engine.Model.Item;
import Game.Model.State.Inventory;
import Game.Model.State.PlayerHealth;

public class GoldenApple extends Item {
    public GoldenApple(int x, int y) {
        super(x, y, 'G', "Golden Apple");
        this.name = "GoldenApple";
    }

    @Override
    public void effect(Engine engine) {
        //TODO
        //Heal player for 1 health
        PlayerHealth health = (PlayerHealth) engine.getState("PlayerHealth");
        health.increaseBy(10);
    }

    @Override
    public void onInteract(Engine engine) {
        System.out.println("You picked up a Golden Apple (+5 health), it had been added to your inventory!");
        Inventory inventory = (Inventory) engine.getState(Inventory.class.getName());
        inventory.addItem(this);
    }

    @Override
    public boolean isBlocking() {
        return super.isBlocking();
    }

}
