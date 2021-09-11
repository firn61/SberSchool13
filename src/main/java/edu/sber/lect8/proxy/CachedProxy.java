package edu.sber.lect8.proxy;

import edu.sber.lect8.CacheProxyRunner;
import edu.sber.lect8.annotatons.Cached;
import edu.sber.lect8.cacheHolders.*;
import edu.sber.lect8.enums.CacheType;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

public class CachedProxy implements InvocationHandler {

    private Object target;
    private SimpleHolder inMemoryHolder = new InMemorySimpleHolder();
    private FileHolder plainFileHolder = new OnDiskHolder();
    private FileHolder zipFileHolder = new ZIpOnDiskHolder();
    private FileHolder currentFileHolder;
    private String rootDirectory;

    public CachedProxy(Object target) {
        this.target = target;
    }

    public CachedProxy(Object target, String rootDirectory) {
        this.target = target;
        this.rootDirectory = rootDirectory;
    }

    public <T> T cache(T instance) {
        T cachedInstance = (T) Proxy.newProxyInstance(CacheProxyRunner.class.getClassLoader(),
                instance.getClass().getInterfaces(), new CachedProxy(instance, rootDirectory));
        return cachedInstance;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("");
        if (method.isAnnotationPresent(Cached.class)) {
            String methodName = method.getName();
            Cached annotation = method.getAnnotation(Cached.class);
            boolean isListedResult = false;
            int resultLimiter = 0;
            if (List.class.isAssignableFrom(method.getReturnType()) &&
                    annotation.cutOff() != Integer.MAX_VALUE) {
                isListedResult = true;
                resultLimiter = annotation.cutOff();
            }
            if (annotation.cacheType().equals(CacheType.FILE)) {
                if (annotation.zip()) {
                    currentFileHolder = zipFileHolder;
                } else {
                    currentFileHolder = plainFileHolder;
                }
                String fileNamePrefix = annotation.fileNamePrefix().equals("") ? method.getName() : annotation.fileNamePrefix();
                String filePath = rootDirectory + fileNamePrefix;
                currentFileHolder.setPath(filePath);
                if (currentFileHolder.isPresent(methodName, args)) {
                    return currentFileHolder.get(methodName, args);
                } else {
                    Object result = method.invoke(target, args);
                    if (isListedResult) {
                        currentFileHolder.add(methodName, args, result, resultLimiter);
                    } else {
                        currentFileHolder.add(methodName, args, result);
                    }
                    return result;
                }
            } else {
                if (inMemoryHolder.isPresent(methodName, args)) {
                    return inMemoryHolder.get(methodName, args);
                } else {
                    Object result = method.invoke(target, args);
                    if (isListedResult) {
                        inMemoryHolder.add(methodName, args, result, resultLimiter);
                    } else {
                        inMemoryHolder.add(methodName, args, result);
                    }
                    return result;
                }
            }
        } else {
            return method.invoke(target, args);
        }
    }

}
