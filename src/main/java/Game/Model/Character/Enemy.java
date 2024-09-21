package Game.Model.Character;

import Engine.Engine;
import Engine.Model.Character;
import Engine.Model.Direction;
import Game.Model.State.PlayerHealth;

import java.util.Random;

public class Enemy extends Character {
    Integer health;
    public Enemy(int x, int y) {
        super(x, y, 'E', "Enemy");
        this.health = 4;
    }

    @Override
    public int[] moveCharacter(Direction direction) {
        System.out.println("You can't control an enemy !");
        return new int[]{this.x, this.y};
    }

    public boolean onInteract (Engine engine) {
        PlayerHealth playerHealth = (PlayerHealth) engine.getState(PlayerHealth.class.getName());
        System.out.println("You're being attacked by an enemy !");

        if (playerHealth.getValue() > this.health) {
            playerHealth.reduceBy(this.health);
            System.out.println("Phew! you survived your remaining health is: "+ playerHealth.getValue());
            return true;
        } else {
            System.out.println("Oh no ! the enemy killed you !");
            playerHealth.setValue(0);
            return false;
        }
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
