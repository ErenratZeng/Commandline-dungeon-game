package Engine.Model;

import Engine.Engine;

public abstract class Transition  extends  MazeElement{


    public  Transition(int x, int y, char s) {
        super(x, y, s);
    }

    public  abstract void applyTransition(Engine engine);
}
