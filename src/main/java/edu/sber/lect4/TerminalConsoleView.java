package edu.sber.lect4;

import java.util.Scanner;

public class TerminalConsoleView implements TerminalView {

    private Terminal terminalImpl;
    Scanner scanner = new Scanner(System.in);

    @Override
    public void sendAction(String name) {
        terminalImpl.buttonPressed(name);
    }

    @Override
    public void setTerminalImpl(TerminalImpl terminalImpl) {
        this.terminalImpl = terminalImpl;
        while (true) {
            terminalImpl.buttonPressed(scanner.next());
        }
    }

    @Override
    public void updateCodeLabel(String maskedPin) {
        System.out.println(maskedPin);
    }

    @Override
    public void pushException(Exception e) {
        pushException(e.getMessage());
    }

    @Override
    public void pushException(String message) {
        System.out.println(message);
    }

    @Override
    public void updateBalanceLabel(long amount) {
        if (amount != Long.MIN_VALUE) {
            System.out.println(amount);
        }
    }
}
