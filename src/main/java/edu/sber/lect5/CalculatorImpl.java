package edu.sber.lect5;

public class CalculatorImpl implements Calculator {
    @Override
    public int calc(int number) {
        if (number == 0) {
            return 1;
        } else {
            return (number * calc(number - 1));
        }
    }
}

