package edu.sber.lect4;

public interface TerminalView {

    void setTerminalImpl(TerminalImpl terminalImpl);
    void updateCodeLabel(String maskedPin);
    void pushException(Exception e);
    void pushException(String message);
    void updateBalanceLabel(long amount);
    void sendAction(String name);

}
