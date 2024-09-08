package Game.Models;

import Engine.Model.Character;

import java.util.Random;

public class Enemy extends Character {
    public Enemy(int x, int y, char c) {
        super(x, y, c);
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
