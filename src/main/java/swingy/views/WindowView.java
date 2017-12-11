package swingy.views;

import swingy.models.character.Character;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class WindowView extends JFrame implements View {
    public WindowView() {
        this.setSize(400, 400);
        this.setTitle("Swingy");
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void showCharacterSelection(ArrayList<Character> characters) {

    }

    public int getOption() {
        return 0;
    }

    public void displayMessage(String msg) {

    }

    public boolean goToPreviousMenu() {
        return false;
    }

    public void showStartMenu() {

    }

    public void exit() {

    }

    public void showCharacterCreation() {

    }

    public String[] getNameClass() {
        return new String[0];
    }

    public void showPlayScreen(Character character) {

    }

    public void showYesNoDialog(String question) {

    }

    public boolean getAnswer() {
        return false;
    }

    public void showNavigationOptions(Character character) {

    }

    public void showFightSummary(ArrayList<String> summary) {

    }
}
