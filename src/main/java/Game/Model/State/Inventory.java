package Game.Model.State;

import Engine.Model.GameState;
import Engine.Model.Item;

import java.util.ArrayList;

/**
 * This class implements the behaviour of the inventory system.
 *
 * @author Christo Antony.
 */
public class Inventory extends GameState<ArrayList<Item>> {

    /**
     * Class constructor with no arguments. Initialises the inventory
     * with no contents.
     */
    public Inventory() {
        super(new ArrayList<>());
    }

    /**
     * Given a found item, add it to the player's inventory.
     *
     * @param item A collectible entity found in a level.
     */
    public void addItem(Item item) {
        this.value.add(item);
    }

    /**
     * Retrieves the item from the player's inventory according to its
     * numerical position in the list.
     *
     * @param item The integer value that designates a particular item.
     * @return The item corresponding to the integer input in the list.
     */
    public Item getItem(int item) {
        assert -1 < item && item < this.value.size();
        return this.value.get(item);
    }

    /**
     * Retrieves different types of items in the inventory.
     *
     * @param itemClass The item of interest.
     * @return A particular item, or none if the item does not exist in the
     *         inventory.
     * @param <T> Generalisation of the Item class to encompass the different
     *            types of items.
     */
    public <T extends Item> T getItem(Class<T> itemClass) {
        for (Item item : this.value) {
            if (itemClass.isInstance(item)) {
                return itemClass.cast(item);
            }
        }
        return  null;
    }

    /**
     * Discard an item from the inventory without using its effects.
     *
     * @param item The item's name.
     */
    public void removeItem(Item item) {
        this.value.remove(item);
    }

    /**
     * Print out all the items in the inventory on the terminal.
     */
    @Override
    public void print() {
        System.out.println("Items in inventory:");
        for (int i = 0; i < this.value.size(); i++) {
            System.out.format("%d. %s (1x)%n", i+1, this.value.get(i).getName());
        }
    }
}
