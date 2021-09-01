package edu.sber.lect5;

import java.lang.reflect.Proxy;

public class CalculatorRunner {
    public static void main(String[] args) throws InterruptedException {

        Calculator calc = new CalculatorImpl();
        Calculator calcProxyInstance = (Calculator) Proxy.newProxyInstance(CalculatorRunner.class.getClassLoader(),
                calc.getClass().getInterfaces(), new PerformanceProxyInvocationHandler(calc));
       // System.out.println(calcProxyInstance.calc(50));

        Calculator calc2 = new PerformanceProxy(new CalculatorImpl());
        System.out.println(calc2.calc(5));

        Calculator cachedCalc = (Calculator) Proxy.newProxyInstance(CalculatorRunner.class.getClassLoader(),
                calc.getClass().getInterfaces(), new CacheProxyInvocationHandler(calc, StorageType.DISK));
        System.out.println("5" + cachedCalc.calc(5));
        System.out.println("5" + cachedCalc.calc(6));
        System.out.println("5" + cachedCalc.calc(5));
        System.out.println("6" + cachedCalc.calc(6));
        System.out.println("5" + cachedCalc.calc(5));
        System.out.println("7" + cachedCalc.calc(7));
        System.out.println("7" + cachedCalc.calc(20));

    }
}
