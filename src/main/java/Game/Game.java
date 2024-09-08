package Game;

import Engine.Engine;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

public class Game {

    static  ArrayList<String> WelcomeScreenControls = new ArrayList<>(Arrays.asList("accept", "decline"));
    public static void main(String[] args) {
        Engine engine = null;
        try {
            engine = new Engine("src/main/java/Game/GameConfig.json");
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException(e);
        }
        engine.print_title_screen();
        switch (engine.inputController.getInput(WelcomeScreenControls)) {
            case 0 :
                engine.printMap();
                break;
            case 1 :
                System.out.println("Thank you for playing !");
                break;
            default:
                System.out.println("Should not happen !");
                break;
        }





    }
}
