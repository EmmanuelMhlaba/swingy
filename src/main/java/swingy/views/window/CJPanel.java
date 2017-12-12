package swingy.views.window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public abstract class CJPanel extends JPanel {
    protected String name;
    protected JLabel label = new JLabel("", SwingConstants.CENTER);
    protected JButton button = new JButton("Ok");

    public CJPanel(String name) {
        this.name = name;
        setLabelText(name);
        label.setPreferredSize(new Dimension(380, 25));
        this.add(label);
    }

    public String getPanelName () {
        return name;
    }

    public void setButtonText (String s) {
        button.setText(s);
    }

    public void setLabelText (String s) {
        label.setText(s);
    }

    public void setButtonListener (ActionListener actionListener) {
        button.addActionListener(actionListener);
    }
}