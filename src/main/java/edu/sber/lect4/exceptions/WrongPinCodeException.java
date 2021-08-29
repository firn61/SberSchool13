package edu.sber.lect4.exceptions;

public class WrongPinCodeException extends Exception{
   public WrongPinCodeException(Throwable cause){
        super("Неверный pin", cause);
    }
}
