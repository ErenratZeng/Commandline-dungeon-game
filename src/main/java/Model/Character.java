package Model;

public class Character {
    int health;

    public Character(int health){
        this.health = health;
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
