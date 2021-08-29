package edu.sber.lect4.exceptions;

public class NoSuchBanknotesException extends Exception{
    public NoSuchBanknotesException(){
        super("Illegal banknotes");
    }
}
