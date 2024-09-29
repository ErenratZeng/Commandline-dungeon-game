package Game.Model.Character;

import Engine.Engine;
import Engine.Model.Character;
import Engine.Model.Direction;
import Game.Model.Item.Sword;
import Game.Model.State.Inventory;
import Game.Model.State.PlayerHealth;

import java.util.Random;

/**
 * This class governs enemy behaviour and interaction.
 *
 * Written by Christo Antony and Qiutong Zeng.
 */
public class Enemy extends Character {
    Integer damage;

    /**
     * Class constructor. Current iteration of all enemies is one with 4 HP
     * (and consequently 4 damage points).
     *
     * @param x Horizontal coordinate.
     * @param y Vertical coordinate.
     */

    public Enemy(int x, int y) {
        super(x, y, 'E', "Enemy");
        this.damage = setEnemyHealth();
    }

    /**
     * Given a direction, return a list of coordinates the enemy will correctly move to.
     * Currently, return an error message for the entity being able to move.
     *
     * @param direction
     * @return List of updated coordinates.
     */
    @Override
    public int[] moveCharacter(Direction direction) {
        System.out.println("Your enemies attempted to move, but they are unable to do so without a nearby life" +
                " force to siphon from.");
        return new int[]{this.x, this.y};
    }

    /**
     * Update player health based on the interaction between them and the
     * enemy.
     *
     * @param engine The config. JSON file the game is stored in.
     * @return True if the player survives the encounter, otherwise false.
     */
    public boolean onInteract (Engine engine) {
        PlayerHealth playerHealth = engine.getState(PlayerHealth.class);
        Inventory inventory = engine.getState(Inventory.class);
        Sword sword = inventory.getItem(Sword.class);

        System.out.println("The enemy approaches! Send them back to the abyss!");

        if (sword != null) {
            System.out.println("With the aging unwieldy sword in your possession, your attacks are no greater than" +
                    " that of a novice cook unable to even slice through fruits. But its residual energy," +
                    " together with your strength, was able to cleave the foe, causing them to dissipate" +
                    " like a dream upon waking.");
            System.out.println("You decide the discard the sword itself, now fully deprived of power and purpose.");
            System.out.println("No HP was lost.");
            inventory.removeItem(sword);
            return true;
        }
        if (playerHealth.getValue() > this.damage) {
            playerHealth.reduceBy(this.damage);
            System.out.println("You sustained injuries, yet you still persevere.");
            System.out.println("Current HP: " + playerHealth.getValue());            return true;
        } else {
            System.out.println("And with a decisive blow, your foe defeats you.");
            System.out.println("You have died, and so this shall forever be your tomb.");
            playerHealth.setValue(0);
            return false;
        }
    }
    public Integer getDamage() {
        return damage;
    }

    /**
     * Sets the HP of the enemy by a random value.
     *
     * @return Randomised HP of the enemy entity.
     */

    public int setEnemyHealth(){
        Random random = new Random();
        int enemyHealth = random.nextInt(7);
        if (enemyHealth == 0){
            return enemyHealth + 1;
        }
        return enemyHealth;
    }

}
