package Game.Model.Item;

import Engine.Engine;
import Engine.Model.Item;

public class Rock extends Item {
    public Rock(int x, int y) {
        super(x, y, '#', "Rock");
        this.name = "Rock";
    }

    @Override
    public boolean isBlocking() {
        return true;
    }


    @Override
    public void onInteract(Engine engine) {
        System.out.println("The move is blocked by a rock !");
    }

    @Override
    public void effect(Engine engine) {
        System.out.println("You can't use a rock");
    }
}
