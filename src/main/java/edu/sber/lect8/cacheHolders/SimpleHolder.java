package edu.sber.lect8.cacheHolders;

public interface SimpleHolder {
    Object get(String name, Object[] args);
    void add(String name, Object[] args, Object result);
    void add(String name, Object[] args, Object result, int elLimit);
    boolean isPresent(String name, Object[] args);
}
