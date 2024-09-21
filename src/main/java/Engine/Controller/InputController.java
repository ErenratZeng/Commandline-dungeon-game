package Engine.Controller;

import Engine.GameConfig;

import java.util.*;
import java.util.stream.Collectors;

public class InputController {
    private HashMap<String, String> keyMap;
    private Scanner scanner;

    public InputController(GameConfig config, Scanner scanner) {
        this.scanner = scanner;
        this.keyMap = new HashMap<>();
        for (GameConfig.ControlConfig c : config.getControls()) {
            String controlName = c.getName();
            for (String key : c.getInputKeys()) {
                keyMap.put(key, controlName);
            }
        }

    }

    @SafeVarargs
    public final String getInput(String prompt, String context, ArrayList<String>... inputs) {
        List<String> validInputs = Arrays.stream(inputs)
                .flatMap(Collection::stream).toList();
        while (true) {
            if (prompt != null) System.out.println(prompt);
            if (context != null) System.out.print(context + "> ");
            else System.out.print("> ");
            String input = scanner.nextLine();
            String controlName = keyMap.get(input.trim().toLowerCase());
            if (controlName == null) {
                System.out.println("The command entered is invalid, try again !");
                continue;
            }
            if (!validInputs.contains(controlName)) {
                System.out.println("The command is not valid in this context, try again !");
                continue;
            }
            return controlName;
        }
    }

    public Integer getIntegerInput(Integer min, Integer max, String prompt, String context) {
        int intInput;
        while (true) {
            if (prompt != null) System.out.println(prompt);
            if (context != null) System.out.print(context + "> ");
            else System.out.print("> ");
            String input = scanner.nextLine();
            try {
                intInput = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid integer between");
                continue;
            }
            if (intInput >= min && intInput <= max) return intInput;
            System.out.format("Enter a integer between %d - %d ", min, max);
        }
    }


}
