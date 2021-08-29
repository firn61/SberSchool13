package edu.sber.lect4.exceptions;

public class NonDigitButtonPressedException extends RuntimeException {
    public NonDigitButtonPressedException(char unexpectedChar) {
        super("Недопустимый символ: " + unexpectedChar);
    }
}