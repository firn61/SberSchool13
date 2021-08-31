package edu.sber.lect5;

import java.lang.reflect.Proxy;

public class PerformanceProxy implements Calculator {

    private Calculator proxyInstance;

    public PerformanceProxy(Calculator realInstance) {
        proxyInstance = (Calculator) Proxy.newProxyInstance(PerformanceProxy.class.getClassLoader(),
                realInstance.getClass().getInterfaces(), new PerformanceProxyInvocationHandler(realInstance));
    }

    @Override
    public long calc(int number) {
        return proxyInstance.calc(number);
    }

}