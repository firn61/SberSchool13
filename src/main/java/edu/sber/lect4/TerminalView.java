package edu.sber.lect4;

import javax.swing.*;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TerminalView extends JFrame {

    private GridBagConstraints constraints = new GridBagConstraints();
    private JLabel label;

    private ActionListener al = e -> {
        JButton o = (JButton) e.getSource();
        String name = o.getText();
        updateCodeView(name.charAt(0), 2);
    };

    public void updateCodeView(char buttonText, int digitNum){
        char[] chars = label.getText().toCharArray();
        chars[digitNum] = buttonText;
        label.setText(String.valueOf(chars));
    }

    private void addComponent(Component component, int x, int y) {
        constraints.gridx = x;
        constraints.gridy = y;
        add(component, constraints);
    }

    private void addButton(JButton button, int x, int y) {
        constraints.gridx = x;
        constraints.gridy = y;
        button.addActionListener(al);
        add(button, constraints);
    }


    public void initFrame() {

        setLayout(new GridBagLayout());
        int buttonCounter = 0;
        char a = 'a';
        label = new JLabel("....");
        label.setFont(new Font("TimesRoman", Font.BOLD, 22));
        addComponent(label, 1, 0);
        addButton(new JButton(String.valueOf(buttonCounter++)), 1, 4);
        for (int y = 1; y < 4; y++) {
            for (int x = 0; x < 3; x++) {
                JButton nButton = new JButton(String.valueOf(buttonCounter++));
                addButton(nButton, x, y);
                JButton wButton = new JButton(String.valueOf(a++));
                addButton(wButton, x, y + 5);
            }
        }
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(150, 270);
        this.setLocation(200, 200);
        this.setVisible(true);

    }
}
