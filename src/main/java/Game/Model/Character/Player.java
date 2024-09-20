package Game.Model.Character;

import Engine.Model.Character;
import Engine.Model.Direction;

public class Player extends Character {

    public Player(int x, int y) {
        super(x, y, 'P');
    }

    @Override
    public int[] moveCharacter(Direction direction){
        int newX = this.x;
        int newY = this.y;

        switch (direction){
            case UP:
                newX -= 1;
                break;
            case DOWN:
                newX += 1;
                break;
            case LEFT:
                newY -= 1;
                break;
            case RIGHT:
                newY += 1;
                break;
        }
        return new int[]{newX, newY};
    }
}
