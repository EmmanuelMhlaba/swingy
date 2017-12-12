package swingy.controllers;

import swingy.models.character.CharacterModel;
import swingy.views.WindowView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WindowController {
    private WindowView view;
    private CharacterModel characterModel;

    public WindowController(WindowView view, CharacterModel characterModel) {
        this.view = view;
        this.characterModel = characterModel;
        this.view.addLoadCharacterButtonListener(new LoadCharacterScreen());
    }

    private class LoadCharacterScreen implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            view.showLoadCharacterScreen();
        }
    }
}
