package edu.sber.lect5;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class MetodsGetter {

    public static void main(String[] args) {
        C2 c2 = new C2();
        Class<?> clazz = c2.getClass();
        Map<Method, String> methodsMap = new HashMap<>();
        while (clazz != null) {
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                methodsMap.put(method, clazz.getCanonicalName());
            }
            clazz = clazz.getSuperclass();
        }
        System.out.println("===== class hierarchy methods =====");
        for (Map.Entry<Method, String> e : methodsMap.entrySet()) {

            System.out.println("c: " + e.getValue() + " " + e.getKey());
        }
        System.out.println("===== class getters =====");
        for (Map.Entry<Method, String> methodStringEntry : methodsMap.entrySet()) {
            if ((!methodStringEntry.getKey().getReturnType().equals(Void.TYPE)) &&
                    (methodStringEntry.getKey().getName().startsWith("get") &&
                            (methodStringEntry.getKey().getParameterCount() == 0))) {
                System.out.println(methodStringEntry.getKey());
            }
        }


    }
}


abstract class C1 {

    Integer a1;
    private String a2;
    public Boolean a3;

    private void f1() {
        int localvar1;
    }

    ;

    public abstract Integer f2();

    private Integer getA1() {
        return a1;
    }

    public void setA1(Integer a1) {
        this.a1 = a1;
    }

    public String getA2() {
        return a2;
    }

    public void setA2(String a2) {
        this.a2 = a2;
    }

    public Boolean getA3() {
        return a3;
    }

    public void setA3(Boolean a3) {
        this.a3 = a3;
    }
}

class C2 extends C1 {
    private String e1;
    public String e2;

    @Override
    public Integer f2() {
        return 0;
    }

    public String getE1() {
        return e1;
    }

    public void setE1(String e1) {
        this.e1 = e1;
    }

    public String getE2() {
        return e2;
    }

    public void setE2(String e2) {
        this.e2 = e2;
    }
}
