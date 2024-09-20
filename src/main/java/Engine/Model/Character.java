package Engine.Model;

public abstract class Character extends MazeElement {

    private char symbol;
    private String description;

    public Character(int x, int y, char c){
        super(x, y, c);
    }
    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
    }

    public abstract int[] moveCharacter(Direction direction);
}
