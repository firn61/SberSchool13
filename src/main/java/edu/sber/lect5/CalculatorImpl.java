package edu.sber.lect5;

public class CalculatorImpl implements Calculator {


    @Override
    public long calc(int number) throws InterruptedException {
        //Thread.sleep(number);
        if (number == 0) {
            return 1;
        } else {
            return (number * calc(number - 1));
        }
    }
}