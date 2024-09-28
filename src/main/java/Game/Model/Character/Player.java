package Game.Model.Character;

import Engine.Engine;
import Engine.Model.Character;
import Engine.Model.Direction;

public class Player extends Character {

    public Player(int x, int y) {
        super(x, y, 'P', "Player");
    }

    @Override
    public int[] moveCharacter(Direction direction){
        int newX = this.x;
        int newY = this.y;

        switch (direction){
            case UP:
                newY -= 1;
                break;
            case DOWN:
                newY += 1;
                break;
            case LEFT:
                newX -= 1;
                break;
            case RIGHT:
                newX += 1;
                break;
        }
        return new int[]{newX, newY};
    }

    @Override
    public boolean onInteract(Engine engine) {
        System.err.println("The player shouldn't interact with anyone");
        return false;
    }
}
