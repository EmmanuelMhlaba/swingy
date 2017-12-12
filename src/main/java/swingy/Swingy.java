package swingy;

import swingy.controllers.ConsoleController;
import swingy.controllers.WindowController;
import swingy.models.character.CharacterModel;
import swingy.views.ConsoleView;
import swingy.views.window.WindowView;

public class Swingy {
    public static void main (String[] args) {
        if (args.length == 1) {
            if (args[0].equals("console")) {
                new ConsoleController(new ConsoleView(), new CharacterModel());
            } else if (args[0].equals("window")) {
                new WindowController(new WindowView(), new CharacterModel());
            } else {
                System.out.println ("Please specify 'console' for console mode or 'window' for windowed mode.");
            }
        } else {
            System.out.println ("Please specify 'console' for console mode or 'window' for windowed mode.");
        }
    }
}
