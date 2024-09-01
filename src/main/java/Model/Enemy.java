package Model;

import java.util.Random;

public class Enemy extends Character {
    public Enemy(int health) {
        super(health);
    }

    @Override
    public int getHealth() {
        return super.getHealth();
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
