package swingy.views.window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CJPanel1 extends CJPanel {
    private JTextArea textArea = new JTextArea();
    private JTextField textField = new JTextField();

    public CJPanel1(String name) {
        super(name);
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(380, 300));
        textField.setPreferredSize(new Dimension(200, 25));
        this.add(scrollPane);
        this.add(textField);
        this.add(button);
    }

    public void setTextFieldText (String s) {
        textField.setText(s);
    }

    public void setTextAreaText (String s) {
        textArea.setText(s);
    }

    public void appendTextAreaText (String s) {
        textArea.append(s);
    }

    public String getTextFieldText () {
        return textField.getText();
    }
}
