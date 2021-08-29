package edu.sber.lect4.exceptions;

public class InsufficientFundsException extends Exception{

    public InsufficientFundsException(long shortage) {
        super("There is not enough money, need more: " + shortage);
    }
}
