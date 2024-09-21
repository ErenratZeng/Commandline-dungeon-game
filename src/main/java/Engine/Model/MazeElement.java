package Engine.Model;

public abstract class MazeElement {
    protected  int x;

    protected  int y;
    protected  char symbol;

    protected String name;


    public char getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    MazeElement(int x, int y, char s, String name
    ) {
        this.x = x;
        this.y = y;
        this.symbol = s;
        this.name = name;
    }

    public boolean isBlocking() {
        return true;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }
}
