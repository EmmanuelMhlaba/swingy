package swingy.views;

import swingy.models.character.Character;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class WindowView extends JFrame implements View {
    private JTextArea taOutput;
    private JTextField tfInput;
    private JButton btnSubmit;

    public WindowView() {
        createView();
        this.setTitle("Swingy");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setSize(400, 400);
        this.setResizable(false);
        this.setVisible(true);
    }

    private void createView () {
        JPanel panel = new JPanel();
        getContentPane().add(panel);
        taOutput = new JTextArea();
        taOutput.setLineWrap(true);
        taOutput.setWrapStyleWord(true);
        taOutput.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(taOutput);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(380, 330));
        panel.add(scrollPane);
        JLabel label = new JLabel("Enter: ");
        panel.add(label);
        tfInput = new JTextField(15);
        panel.add(tfInput);
        btnSubmit = new JButton("Submit");
        panel.add(btnSubmit);
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
