package Game.Model.State;

import Engine.Model.GameState;
import Engine.Model.Item;

import java.util.ArrayList;

public class Inventory  extends GameState<ArrayList<Item>> {
    public Inventory() {
        super(new ArrayList<>()); // initialize the Inventory to b empty
    }
    public void addItem (Item item) {
        this.value.add(item);
    }

    public Item getItem (int item) {
        assert  -1 <  item && item < this.value.size();
        return this.value.get(item);
    }
    public void removeItem (Item item) {
        this.value.remove(item);
    }
    @Override
    public void printGameState() {
        System.out.println("Items in inventory:");
        for (int i = 0; i < this.value.size(); i++) {
            System.out.format("%d. %s (1x)%n", i+1, this.value.get(i).getName());
        }

    }
}
