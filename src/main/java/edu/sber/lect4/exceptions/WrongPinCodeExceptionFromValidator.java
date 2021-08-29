package edu.sber.lect4.exceptions;

public class WrongPinCodeExceptionFromValidator extends RuntimeException {
    public WrongPinCodeExceptionFromValidator() {
        super("Incorrect pin");
    }
}