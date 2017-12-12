package swingy.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class WindowView {
    JPanel mainPanel = new JPanel();
    CardLayout cardLayout = new CardLayout();
    // Start page buttons
    JButton btnNewGame = new JButton("New game");
    JButton btnLoadGame = new JButton("Load game");

    public WindowView() {
        mainPanel.setLayout(cardLayout);
        createCharacterCreatePage();
        createCharacterLoadPage();
        createStartPage();
        cardLayout.show(mainPanel, "StartPanel");
        setupFrame();
    }

    private void setupFrame () {
        JFrame mainFrame = new JFrame();
        mainFrame.add(mainPanel);
        mainFrame.setTitle("Swingy");
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setSize(400, 400);
        mainFrame.setResizable(false);
        mainFrame.setVisible(true);
    }

    private void createCharacterLoadPage() {
        JPanel characterSelectPanel = new JPanel();
        characterSelectPanel.setBackground(Color.BLUE);
        mainPanel.add(characterSelectPanel, "CharacterLoadPanel");
    }

    private void createStartPage () {
        JPanel startPanel = new JPanel();
        JLabel lblPage = new JLabel("Main Menu", SwingConstants.CENTER);
        lblPage.setPreferredSize(new Dimension(390, 50));
        startPanel.add (lblPage);
        startPanel.add (btnLoadGame);
        startPanel.add (btnNewGame);
        mainPanel.add(startPanel, "StartPanel");
    }

    private void createCharacterCreatePage () {
        JPanel characterCreatePanel = new JPanel();
        characterCreatePanel.setBackground(Color.GREEN);
        mainPanel.add(characterCreatePanel, "CharacterCreatePanel");
    }

    public void addLoadCharacterButtonListener(ActionListener actionListener) {
        btnLoadGame.addActionListener(actionListener);
    }

    public void showLoadCharacterScreen () {
        cardLayout.show(mainPanel, "CharacterLoadPanel");
    }
}
