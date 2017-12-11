package swingy;

import swingy.controllers.CharacterController;
import swingy.models.character.CharacterModel;
import swingy.views.ConsoleView;
import swingy.views.WindowView;

public class Swingy {
    public static void main (String[] args) {
        if (args.length == 1) {
            if (args[0].equals("console")) {
                CharacterController characterController = new CharacterController(new ConsoleView(), new CharacterModel());
                characterController.startGame();
            } else if (args[0].equals("window")) {
                CharacterController characterController = new CharacterController(new WindowView(), new CharacterModel());
                characterController.startGame();
            } else {
                System.out.println ("Please specify 'console' for console mode or 'window' for windowed mode.");
            }
        } else {
            System.out.println ("Please specify 'console' for console mode or 'window' for windowed mode.");
        }
    }
}
