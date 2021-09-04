package edu.sber.lect5;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class BeanUtils {
    /**
     * Scans object "from" for all getters. If object "to"
     * contains correspondent setter, it will invoke it
     * to set property value for "to" which equals to the property
     * of "from".
     * <p/>
     * The type in setter should be compatible to the value returned
     * by getter (if not, no invocation performed).
     * Compatible means that parameter type in setter should
     * be the same or be superclass of the return type of the getter.
     * <p/>
     * The method takes care only about public methods.
     *
     * @param to   Object which properties will be set.
     * @param from Object which properties will be used to get values.
     */
    public static void assign(Object to, Object from) {
        Method[] methods = from.getClass().getDeclaredMethods();
        for (Method method : methods) {
            if ((method.getReturnType() != Void.TYPE)
                    && (method.getName().startsWith("get"))
                    && (method.getParameterCount() == 0)
                    && (method.getModifiers() == 1)) {
                Class<?> klazz = method.getReturnType();
                List<Class<?>> klazzs = new ArrayList<>();
                while (klazz != null) {
                    klazzs.add(klazz);
                    klazz = klazz.getSuperclass();
                }
                for (Class<?> clazz : klazzs) {
                    try {
                        Method toMethodCandidate = to.getClass()
                                .getDeclaredMethod(method.getName()
                                        .replace("get", "set"), clazz);
                        if (toMethodCandidate.getModifiers() == 1) {
                            toMethodCandidate.invoke(to, method.invoke(from));
                        }
                        break;
                    } catch (NoSuchMethodException e) {
                         e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
