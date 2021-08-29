package edu.sber.lect4.exceptions;

public class AccountBlockedException extends RuntimeException{
    public AccountBlockedException(long blockedTime) {
        super("Аккаунт заблокирован на " + blockedTime / 1000d + " сек");
    }
}
