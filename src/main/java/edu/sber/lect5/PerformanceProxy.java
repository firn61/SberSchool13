package edu.sber.lect5;

public class PerformanceProxy implements Calculator {

    private Calculator proxyInstance;

    public PerformanceProxy(Calculator proxyInstance) {
        this.proxyInstance = proxyInstance;
    }

    @Override
    public long calc(int number) throws InterruptedException {
        try {
            Class<?>[] cli = proxyInstance.getClass().getInterfaces();
            for (Class<?> aClass : cli) {
                if (aClass.getMethod(new Throwable().getStackTrace()[0].getMethodName(), int.class) != null) {
                    if (aClass.getMethod("calc", int.class).isAnnotationPresent(Metric.class)) {
                        long start = System.nanoTime();
                        long result = proxyInstance.calc(number);
                        long finished = System.nanoTime();
                        System.out.println("Время выполнение метода "
                                + (finished - start) + "ns");
                        return result;
                    }
                }
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            System.out.println("Something goes wrong");
        }
        return proxyInstance.calc(number);
    }
}