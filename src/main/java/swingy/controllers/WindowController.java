package swingy.controllers;

import swingy.models.character.Character;
import swingy.models.character.CharacterModel;
import swingy.views.window.WindowView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WindowController {
    private WindowView view;
    private CharacterModel characterModel;
    private Character character;

    public WindowController(WindowView view, CharacterModel characterModel) {
        this.view = view;
        this.characterModel = characterModel;
        this.view.setCharacterLoadPanelListener(new CharacterLoadPanelListener());
        this.view.setCharacterCreatePanelListener(new CharacterCreatePanelListener(), new CharacterCreatePanelListener2());
        this.view.setMainMenuPanelListener(new MainMenuPanelListener());
    }

    private class MainMenuPanelListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                int tmp;
                tmp = Integer.parseInt(view.getMainMenuPanelText());
                switch (tmp) {
                    case 0:
                        view.showCharacterCreatePanel();
                        break;
                    case 1:
                        view.showCharacterLoadPanel(characterModel.getCharacters());
                        break;
                    case 2:
                        view.displayMessage("Exit coming soon");
                        break;
                    default:
                        view.displayMessage("Please enter one of the given options");
                        break;
                }
            } catch (NumberFormatException e1) {
                view.displayMessage("Please enter one of the given options");
            }
            view.clearAllTextBoxes();
        }
    }

    private class CharacterLoadPanelListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                int tmp;
                tmp = Integer.parseInt(view.getCharacterLoadPanelText());
                if (tmp >= 0 && tmp <= characterModel.getCharacters().size()) {
                    if (tmp == characterModel.getCharacters().size()) {
                        view.clearAllTextBoxes();
                        view.showMainMenuPanel();
                    } else {
                        character = characterModel.getCharacters().get(tmp);
                        view.displayMessage(character.toString());
                    }
                } else {
                    view.displayMessage("Please enter one of the given options");
                }
            } catch (NumberFormatException e1) {
                view.displayMessage("Please enter one of the given options");
            }
            view.clearAllTextBoxes();
        }
    }

    private class CharacterCreatePanelListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            view.showCharacterCreatePanel();
        }
    }

    private class CharacterCreatePanelListener2 implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            view.showMainMenuPanel();
        }
    }
}
