package swingy.views.window;

import swingy.models.character.Character;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class WindowView {
    JFrame mainFrame = new JFrame();
    JPanel mainPanel = new JPanel();
    CJPanel1 mainMenuPanel = new CJPanel1("Main Menu");
    CJPanel1 characterLoadPanel = new CJPanel1("Character Load");
    CJPanel1 playPanel = new CJPanel1("Play");
    CJPanel2 characterCreatePanel = new CJPanel2("Character Create");
    CardLayout cardLayout = new CardLayout();

    public WindowView() {
        mainPanel.setLayout(cardLayout);
        mainPanel.add(mainMenuPanel, mainMenuPanel.getPanelName());
        mainPanel.add(characterLoadPanel, characterLoadPanel.getPanelName());
        mainPanel.add(characterCreatePanel, characterCreatePanel.getPanelName());
        mainPanel.add(playPanel, playPanel.getPanelName());
        showMainMenuPanel();
        setupFrame();
    }

    private void setupFrame () {
        mainFrame.add(mainPanel);
        mainFrame.setTitle("Swingy");
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setSize(400, 400);
        mainFrame.setResizable(false);
        mainFrame.setVisible(true);
    }

    public void setPlayPanelListener(ActionListener actionListener) {
        playPanel.setButtonListener(actionListener);
    }

    public void setCharacterLoadPanelListener(ActionListener actionListener) {
        characterLoadPanel.setButtonListener(actionListener);
    }

    public void setCharacterCreatePanelListener(ActionListener actionListener1, ActionListener actionListener2) {
        characterCreatePanel.setButtonListener(actionListener1);
        characterCreatePanel.setBackButtonListener(actionListener2);
    }

    public void setMainMenuPanelListener(ActionListener actionListener) {
        mainMenuPanel.setButtonListener(actionListener);
    }

    public void clearAllTextBoxes() {
        mainMenuPanel.setTextFieldText("");
        characterLoadPanel.setTextFieldText("");
        characterCreatePanel.setCharacterClassText("");
        characterCreatePanel.setCharacterNameText("");
        playPanel.setTextFieldText("");
    }

    public String getPlayPanelText () {
        return playPanel.getTextFieldText().trim();
    }

    public String getMainMenuPanelText () {
        return mainMenuPanel.getTextFieldText().trim();
    }

    public String getCharacterLoadPanelText () {
        return characterLoadPanel.getTextFieldText().trim();
    }

    public String[] getCharacterCreatePanelText () {
        String[] tmp = new String[2];
        tmp[0] = characterCreatePanel.getCharacterName().trim();
        tmp[1] = characterCreatePanel.getCharacterClass().trim();
        return tmp;
    }

    public void showPlayPanel () {
        cardLayout.show(mainPanel, playPanel.getPanelName());
    }

    public void showMainMenuPanel() {
        mainMenuPanel.setTextAreaText("");
        mainMenuPanel.appendTextAreaText("===== Main menu =====\n");
        mainMenuPanel.appendTextAreaText("0) Create character\n");
        mainMenuPanel.appendTextAreaText("1) Select character\n");
        mainMenuPanel.appendTextAreaText("2) Exit\n");
        mainMenuPanel.appendTextAreaText("==========\n");
        cardLayout.show(mainPanel, mainMenuPanel.getPanelName());
    }

    public void showCharacterLoadPanel(ArrayList<Character> characters) {
        characterLoadPanel.setTextAreaText("");
        int index = 0;
        characterLoadPanel.appendTextAreaText("===== Character selection =====\n");
        for (Character character : characters) {
            characterLoadPanel.appendTextAreaText(index++ + ") " + character + "\n");
        }
        characterLoadPanel.appendTextAreaText(index + ") Back to previous menu.\n");
        characterLoadPanel.appendTextAreaText("==========\n");
        cardLayout.show(mainPanel, characterLoadPanel.getPanelName());
    }

    public void showCharacterCreatePanel() {

        cardLayout.show(mainPanel, characterCreatePanel.getPanelName());
    }

    public void displayMessage (String msg) {
        JOptionPane.showMessageDialog(mainFrame, msg);
    }

    public CJPanel1 getPlayPanel() {
        return playPanel;
    }

    public void showNavigationOptions(Character character) {
        playPanel.appendTextAreaText ("===== Navigation =====\n");
        playPanel.appendTextAreaText ("Current direction: " + Arrays.toString(character.get_pos()) + "\n");
        playPanel.appendTextAreaText ("Select a direction to move towards\n");
        playPanel.appendTextAreaText ("1) North\n");
        playPanel.appendTextAreaText ("2) East\n");
        playPanel.appendTextAreaText ("3) South\n");
        playPanel.appendTextAreaText ("4) West\n");
        playPanel.appendTextAreaText ("5) Main menu\n");
        playPanel.appendTextAreaText("==========\n");
    }

    public boolean showConfirmDialog (String s) {
        int dialogResult = JOptionPane.showConfirmDialog (null, s,"Note", JOptionPane.YES_NO_OPTION);
        if(dialogResult == JOptionPane.YES_OPTION){
            return true;
        }
        return false;
    }
}
