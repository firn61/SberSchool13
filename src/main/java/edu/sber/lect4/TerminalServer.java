package edu.sber.lect4;

import edu.sber.lect4.exceptions.InsufficientFundsException;
import edu.sber.lect4.exceptions.NoSuchBanknotesException;

public class TerminalServer {

    private long cashAmount;

    TerminalServer(long cashAmount) {
        this.cashAmount = cashAmount;
    }

    public synchronized long withdraw(long cashToWithdraw)
            throws NoSuchBanknotesException, InsufficientFundsException{
        if (cashAmount >= cashToWithdraw) {
            if (cashToWithdraw % 100 == 0) {
                cashAmount -= cashToWithdraw;
                return cashAmount;
            } else {
                throw new NoSuchBanknotesException();
            }
        } else {
            throw new InsufficientFundsException(cashAmount-cashToWithdraw);
        }
    }

    public synchronized long deposit(long cashToDeposit) throws NoSuchBanknotesException{
        if (cashToDeposit % 100 == 0) {
            cashAmount += cashToDeposit;
            return cashAmount;
        } else {
            throw new NoSuchBanknotesException();
        }
    }

    public long getBalance(){
        return cashAmount;
    }

}
