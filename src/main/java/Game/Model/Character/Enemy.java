package Game.Model.Character;

import Engine.Model.Character;
import Engine.Model.Direction;

import java.util.Random;

public class Enemy extends Character {
    Integer health;
    public Enemy(int x, int y) {
        super(x, y, 'E');
        this.name = "Enemy";
    }

    @Override
    public int[] moveCharacter(Direction direction) {
        System.out.println("You can't control an enemy !");
        return new int[]{this.x, this.y};
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
