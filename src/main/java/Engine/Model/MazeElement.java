package Engine.Model;

public abstract class MazeElement {
    int x;
    int Y;
    char symbol;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return Y;
    }

    public void setY(int y) {
        Y = y;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    MazeElement(int x, int y, char s) {
        this.x = x;
        this.Y = y;
        this.symbol = s;
    }
}
