package edu.sber.lect4;

public class TerminalRunner {
    public static void main(String[] args) {
        PinValidator pinValidator = new PinValidator("1234");
       // TerminalView terminalView = new TerminalFrameView();
        TerminalView terminalView = new TerminalConsoleView();
        TerminalServer terminalServer = new TerminalServer(9999);
        TerminalImpl terminalImpl = new TerminalImpl(pinValidator, terminalView, terminalServer);
    }

}
