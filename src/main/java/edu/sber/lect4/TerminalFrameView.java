package edu.sber.lect4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class TerminalFrameView extends JFrame implements TerminalView {

    private GridBagConstraints constraints = new GridBagConstraints();
    private JLabel label;
    private JLabel balance;
    private Terminal terminalImpl;

    @Override
    public void setTerminalImpl(TerminalImpl terminalImpl) {
        this.terminalImpl = terminalImpl;
    }

    @Override
    public void sendAction(String name){
        terminalImpl.buttonPressed(name);
    }

    private ActionListener al = e -> {
        JButton o = (JButton) e.getSource();
        String name = o.getText();
        sendAction(name);
    };

    @Override
    public void updateCodeLabel(String maskedPin) {
        label.setText(maskedPin);
    }

    @Override
    public void pushException(Exception e) {
        pushException(e.getMessage());

    }

    public void pushException(String message) {
        JOptionPane.showMessageDialog(this, message,
                "ERROR", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void updateBalanceLabel(long amount) {
        if (amount == Long.MIN_VALUE) {
            balance.setText("");
        } else {
            balance.setText(String.valueOf(amount));
        }
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


    TerminalFrameView() {
        setLayout(new GridBagLayout());
        int buttonCounter = 0;
        char a = 'a';
        Font font = new Font("TimesRoman", Font.BOLD, 22);
        label = new JLabel("....");
        balance = new JLabel("");
        balance.setFont(font);
        label.setFont(font);
        addComponent(label, 1, 1);
        addComponent(balance, 1, 0);
        addButton(new JButton(String.valueOf(buttonCounter++)), 1, 5);
        for (int y = 2; y < 5; y++) {
            for (int x = 0; x < 3; x++) {
                JButton nButton = new JButton(String.valueOf(buttonCounter++));
                addButton(nButton, x, y);
                JButton wButton = new JButton(String.valueOf(a++));
                addButton(wButton, x, y + 5);
            }
        }
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(180, 300);
        this.setLocation(400, 400);
        this.setVisible(true);

    }
}
