package edu.sber.lect5;

public interface Calculator{
    /**
     * Расчет факториала числа.
     * @param number нумбер
     */
    @Cacheable
    @Metric
    long calc (int number);
}
