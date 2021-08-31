package edu.sber.lect5;

public class CalculatorRunner {
    public static void main(String[] args) {
        Calculator calc = new CalculatorImpl();
        System.out.println(calc.calc(16));
    }
}
