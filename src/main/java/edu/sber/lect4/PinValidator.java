package edu.sber.lect4;

import edu.sber.lect4.exceptions.WrongPinCodeExceptionFromValidator;

public class PinValidator {

    private final String pin;

    public boolean validate(String pin) throws WrongPinCodeExceptionFromValidator {
        if (this.pin.equals(pin)) {
            return true;
        } else {
            throw new WrongPinCodeExceptionFromValidator();
        }
    }

    PinValidator(String pin) {
        this.pin = pin;
    }
}
