package Engine.Model;

public abstract class Character extends MazeElement {
    protected int health;
    private String name;
    private char symbol;
    private String description;

    public Character(int x, int y, char c){
        super(x, y, c);
    }
    public void setPosition(int x, int y){
        super.setX(x);
        super.setY(y);
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(){
    }

    public int takeDamage(int amount){
        return getHealth() - amount;
    }



}
