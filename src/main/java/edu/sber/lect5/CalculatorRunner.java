package edu.sber.lect5;

import java.lang.reflect.Proxy;

public class CalculatorRunner {
    public static void main(String[] args) throws InterruptedException {

        Calculator calc = new CalculatorImpl();
        //System.out.println(calc.calc(16));
        Calculator calcProxyInstance = (Calculator) Proxy.newProxyInstance(CalculatorRunner.class.getClassLoader(),
                calc.getClass().getInterfaces(), new PerformanceProxyInvocationHandler2(calc));
       // System.out.println(calcProxyInstance.calc(50));
        Calculator calc2 = new PerformanceProxy(new CalculatorImpl());
        System.out.println(calc2.calc(5));

    }
}
