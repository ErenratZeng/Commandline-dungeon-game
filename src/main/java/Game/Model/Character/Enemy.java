package Game.Model.Character;

import Engine.Engine;
import Engine.Model.Character;
import Engine.Model.Direction;
import Game.Model.Item.Sword;
import Game.Model.State.Inventory;
import Game.Model.State.PlayerHealth;

import java.util.Random;

public class Enemy extends Character {
    Integer damage;
    public Enemy(int x, int y) {
        super(x, y, 'E', "Enemy");
        this.damage = setEnemyHealth();
    }

    @Override
    public int[] moveCharacter(Direction direction) {
        System.out.println("You can't control an enemy !");
        return new int[]{this.x, this.y};
    }
    public boolean onInteract (Engine engine) {
        PlayerHealth playerHealth = engine.getState(PlayerHealth.class);
        Inventory inventory = engine.getState(Inventory.class);
        Sword sword = inventory.getItem(Sword.class);

        System.out.println("You're being attacked by an enemy !");

        if (sword != null) {
            System.out.println("Good Job! You slayed the enemy with your sword !");
            System.out.println("You didn't lose any health !");
            inventory.removeItem(sword);
            return  true;
        }
        if (playerHealth.getValue() > this.damage) {
            playerHealth.reduceBy(this.damage);
            System.out.println("Phew! you survived your remaining health is: "+ playerHealth.getValue());
            return true;
        } else {
            System.out.println("Oh no ! the enemy killed you !");
            playerHealth.setValue(0);
            return false;
        }
    }

    public Integer getDamage() {
        return damage;
    }

    public int setEnemyHealth(){
        Random random = new Random();
        int enemyHealth = random.nextInt(7);
        if (enemyHealth == 0){
            return enemyHealth + 1;
        }
        return enemyHealth;
    }

}
