package Game.Model.Transition;

import Engine.Engine;
import Engine.Model.Transition;
import Game.Model.State.GameLevel;

public class Staircase extends Transition {

    static final String[] maze_level = {"level_1", "level_2", "level_3"};

    public Staircase(int x, int y) {
        super(x, y, 'Z', "Staircase");
    }

    @Override
    public void applyTransition(Engine engine) {
        GameLevel gameLevel = (GameLevel) engine.getState(GameLevel.class.getName());
        gameLevel.nextLevel();
        String mazeName = maze_level[gameLevel.getValue()-1];
        engine.setCurrentMaze(mazeName);
        System.out.println("you found a staircase, you're moving closer to the exit !");
    }
}
