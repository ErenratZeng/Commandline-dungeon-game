package Engine.Model;

import Engine.Engine;

public abstract class Transition  extends  MazeElement{


    public  Transition(int x, int y, char s, String name) {
        super(x, y, s, name);
    }

    public  abstract void applyTransition(Engine engine);
}
