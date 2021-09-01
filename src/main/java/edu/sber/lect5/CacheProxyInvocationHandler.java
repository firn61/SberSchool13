package edu.sber.lect5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CacheProxyInvocationHandler implements InvocationHandler {

    private Object target;

    private ResultKeeper keeper;

    public CacheProxyInvocationHandler(Object target, StorageType storageType) {
        this.target = target;
        if (storageType.equals(StorageType.RUNTIME)) {
            keeper = new RuntimeResultKeeper();
        } else {
            keeper = new DiskResultKeeper();
        }
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.isAnnotationPresent(Cacheable.class)) {
            System.out.println("Annotation presented");
            MethodWithArgsKeeper mwa = new MethodWithArgsKeeper(method, args);
            if (!keeper.cached(mwa)) {
                System.out.println("Not cached yet");
                Object result = method.invoke(target, args);
                keeper.putResult(mwa, result);
                System.out.println("Cached: " + mwa);
                return result;
            }
            System.out.println("get from cache");
            return keeper.getResult(mwa);
        }
        System.out.println("method not annotated");
        return method.invoke(target, args);
    }
}

class MethodWithArgsKeeper {
    Method method;
    Object[] args;

    public MethodWithArgsKeeper(Method method, Object[] args) {
        this.method = method;
        this.args = args;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MethodWithArgsKeeper that = (MethodWithArgsKeeper) o;
        return Objects.equals(method, that.method) && Arrays.equals(args, that.args);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(method);
        result = 31 * result + Arrays.hashCode(args);
        return result;
    }

    @Override
    public String toString() {
        return "MethodWithArgsKeeper{" +
                "method=" + method +
                ", args=" + Arrays.toString(args) +
                '}';
    }
}

class RuntimeResultKeeper implements ResultKeeper {

    Map<MethodWithArgsKeeper, Object> map = new HashMap<>();

    @Override
    public boolean cached(MethodWithArgsKeeper mwa) {
        return map.containsKey(mwa);
    }

    @Override
    public Object getResult(MethodWithArgsKeeper mwa) {
        return map.get(mwa);
    }

    @Override
    public void putResult(MethodWithArgsKeeper mwa, Object result) {
        map.put(mwa, result);
    }
}

class DiskResultKeeper implements ResultKeeper {

    private Path path = Paths.get("src/main/resources/results.txt");
    private String separator = " : ";

    @Override
    public boolean cached(MethodWithArgsKeeper mwa) {
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            int i = 0;
            String line;
            while ((line = reader.readLine()) != null) {
                if ((line != null) && (line.startsWith(String.valueOf(mwa.hashCode())))) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Object getResult(MethodWithArgsKeeper mwa) {
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(String.valueOf(mwa.hashCode()))) {
                    return Long.valueOf(line.substring(line.indexOf(separator) + separator.length()));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void putResult(MethodWithArgsKeeper mwa, Object result) {
        try (BufferedWriter writer = Files.newBufferedWriter(path,
                Charset.forName("UTF-8"), StandardOpenOption.APPEND)) {
            writer.write(mwa.hashCode() + separator + result);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

interface ResultKeeper {

    boolean cached(MethodWithArgsKeeper mwa);

    Object getResult(MethodWithArgsKeeper mwa);

    void putResult(MethodWithArgsKeeper mwa, Object result);

}