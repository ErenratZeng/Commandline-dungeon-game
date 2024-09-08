package Engine.Model;

public abstract class Character  extends MazeElement {
    private int health;
    private String name;
    private char symbol;
    private String description;

    public Character(int x, int y, char c){
        super(x, y, c);
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
