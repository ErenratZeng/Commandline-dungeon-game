package Engine.Controller;

import Engine.GameConfig;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class InputController {
    private HashMap<String, String> keyMap;
    private Scanner scanner;

    public InputController(GameConfig config, Scanner scanner){
       this.scanner = scanner;
       this.keyMap  = new HashMap<>();
       for (GameConfig.ControlConfig c : config.getControls()) {
           String controlName = c.getName();
           for (String key: c.getInputKeys()) {
               keyMap.put(key, controlName);
           }
       }

    }
    public int getInput (ArrayList<String> validInputs) {
        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();
            String controlName = keyMap.get(input.trim().toLowerCase());
            if (controlName == null){
                System.out.println("The command entered is invalid, try again !");
                continue;
            }
            int response = validInputs.indexOf(controlName);
            if (response == -1) {
                System.out.println("The command is not valid in this context, try again !");
                continue;
            }
            return response;
        }
    }


}
