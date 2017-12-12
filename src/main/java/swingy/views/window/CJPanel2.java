package swingy.views.window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CJPanel2 extends CJPanel {
    private JLabel nameLabel = new JLabel("Name:");
    private JTextField characterName = new JTextField();
    private JLabel classLabel = new JLabel("Class (Warrior or Thief):");
    private JTextField characterClass = new JTextField();
    private JButton backButton = new JButton("Back");

    public CJPanel2(String name) {
        super(name);
        characterName.setPreferredSize(new Dimension(200, 25));
        characterClass.setPreferredSize(new Dimension(200, 25));
        this.add(nameLabel);
        this.add(characterName);
        this.add(classLabel);
        this.add(characterClass);
        this.add(backButton);
        this.add(button);
    }

    public void setBackButtonListener (ActionListener actionListener) {
        backButton.addActionListener(actionListener);
    }
}
