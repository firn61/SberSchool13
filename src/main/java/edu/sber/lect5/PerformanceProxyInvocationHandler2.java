package edu.sber.lect5;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Date;

public class PerformanceProxyInvocationHandler2 implements InvocationHandler {

    Object target;

    public PerformanceProxyInvocationHandler2(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.isAnnotationPresent(Metric.class)) {
            long start = System.nanoTime();
            Object result = method.invoke(target, args);
            long finished = System.nanoTime();
            System.out.println("Время выполнение метода " + method.getName() + " "
                    + (finished - start)+ "ns");
            return result;
        }
        return method.invoke(target, args);
    }
}