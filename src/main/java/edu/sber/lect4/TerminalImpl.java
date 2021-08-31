package edu.sber.lect4;

import edu.sber.lect4.exceptions.*;

import java.util.Date;

public class TerminalImpl implements Terminal{

    private final PinValidator pinValidator;
    private final TerminalView terminalView;
    private final TerminalServer terminalServer;
    private int digitNum = 0;
    private int attemptCount = 0;
    private boolean blocked = false;
    private boolean accessGranted = false;
    private StringBuilder pin = new StringBuilder();
    private StringBuilder sum = new StringBuilder();
    private long blockStartTime;

    public TerminalImpl(PinValidator pinValidator, TerminalView terminalView,
                        TerminalServer terminalServer) {
        this.pinValidator = pinValidator;
        this.terminalView = terminalView;
        this.terminalServer = terminalServer;
        terminalView.setTerminalImpl(this);
    }

    private void sendException(Exception e) {
        terminalView.pushException(e);
    }

    private void sendException(String message) {
        terminalView.pushException(message);
    }

    private void flushSum() {
        sum.delete(0, sum.length());
    }

    private void flushPin() {
        pin.delete(0, pin.length());
        updateLabel();
    }

    private void blockTerminal() {
        blocked = true;
        attemptCount = 0;
        blockStartTime = new Date().getTime();
    }

    private void updateLabel() {
        char[] maskedPin = {'.', '.', '.', '.'};
        int reducedPinLen = pin.length() - 1;
        if (pin.length() > 0) {
            for (int i = 0; i < reducedPinLen; i++) {
                maskedPin[i] = '*';
            }
            maskedPin[reducedPinLen] = pin.charAt(reducedPinLen);
        }
        terminalView.updateCodeLabel(String.valueOf(maskedPin));
    }

    /**
     *
     * @throws AccountBlockedException при непральном вводе PIN кода 3 раза подряд
     */
    private void checkAndResetBlockedState() throws AccountBlockedException {
        if (blocked) {
            long freezeTime = new Date().getTime() - blockStartTime;
            if (freezeTime < 10000) {
                throw new AccountBlockedException(blockStartTime - new Date().getTime() + 10000);
            } else {
                blocked = false;
            }
        }
    }

    private boolean isDigit(char button) throws NonDigitButtonPressedException {
        if (!Character.isDigit(button)) {
            throw new NonDigitButtonPressedException(button);
        } else {
            return true;
        }
    }

    private void exit() {
        accessGranted = false;
        flushSum();
        flushPin();
        attemptCount = 0;
        terminalView.updateBalanceLabel(Long.MIN_VALUE);
    }

    private void prepareToWithdraw() {
        terminalView.updateBalanceLabel(terminalServer.getBalance());
        terminalView.updateCodeLabel("");
    }

    private void noAccessEnter(char cPin) {
        try {
            if (cPin == 'c') {
                flushPin();
                digitNum = 0;
                return;
            }
            checkAndResetBlockedState();
            isDigit(cPin);
            digitNum++;
            pin.append(cPin);
            updateLabel();
            if (digitNum == 4) {
                attemptCount++;
                digitNum = 0;
                if (pinValidator.validate(pin.toString())) {
                    flushPin();
                    attemptCount = 0;
                    accessGranted = true;
                    prepareToWithdraw();
                }
            }
        } catch (NonDigitButtonPressedException | AccountBlockedException e) {
            sendException(e);
        } catch (WrongPinCodeExceptionFromValidator e) {
            flushPin();
            sendException(new WrongPinCodeException(e));
            if (attemptCount >= 3) {
                blockTerminal();
                sendException(new AccountBlockedException(10000));
            }
        }
    }

    private void accessEnter(char cPin) {
        try {
            if (Character.isDigit(cPin)) {
                sum.append(cPin);
                terminalView.updateCodeLabel(sum.toString());
            } else if (cPin == 'c') {
                terminalView.updateCodeLabel("");
                flushSum();
            } else if (cPin == 'e') {
                exit();
            } else if (sum.length() > 0) {
                if (cPin == 'g') {
                    terminalView.updateBalanceLabel(terminalServer.withdraw(Long.valueOf(sum.toString())));
                } else if (cPin == 'd') {
                    terminalView.updateBalanceLabel(terminalServer.deposit(Long.valueOf(sum.toString())));
                }
            } else {
                sendException("Неверный ввод");
            }
        } catch (NoSuchBanknotesException e) {
            sendException("Неверный номинал банкнот, сумма не кратна 100");
        } catch (InsufficientFundsException e) {
            sendException("Недостаточно средств");
        }
    }

    @Override
    public void buttonPressed(String buttonText) {
        char pressedButtonLabel = buttonText.charAt(0);
        if (!accessGranted) {
            noAccessEnter(pressedButtonLabel);
        } else {
            accessEnter(pressedButtonLabel);
        }
    }
}
