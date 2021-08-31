package edu.sber.lect5;

public interface Calculator{
    /**
     * Расчет факториала числа.
     * @param number
     */
    @Metric
    long calc (int number) throws InterruptedException;
}
