package Model;

public class Sword extends Item{
    boolean isUsed;
    public Sword() {
        super("Sword");
        this.isUsed = false;
    }

    @Override
    public void effect(Player player) {
        //TODO
        //By using sword, player can defeat enemy without losing health
    }

    public boolean isUsed(){
        return isUsed;
    }

    public void setUsed(){
        isUsed = true;
    }

}
