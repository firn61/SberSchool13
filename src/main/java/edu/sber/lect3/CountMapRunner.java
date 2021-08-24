package edu.sber.lect3;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class CountMapRunner {

    public static void main(String[] args) {

        CountMap<String> cMap = new CountMapImpl<>();
        CountMap<String> cMap2 = new CountMapImpl<>();
        cMap2.add("hello");
        cMap2.add("hello");
        cMap2.add("bye");
        System.out.println(cMap2.toMap());
        cMap.add("hello");
        cMap.add("hello");
        cMap.add("hello");
        cMap.add("hello");
        cMap.add("world");
        cMap.add("hello");
        System.out.println(cMap.toMap());
        cMap.addAll(cMap2);
        System.out.println(cMap.toMap());
    }
}


class CountMapImpl<T> implements CountMap<T> {

    private final Map<T, Integer> map = new HashMap<>();
    private final Map<T, Integer> lifeTimeMap = new HashMap<>();

    // добавляет элемент в этот контейнер.
    @Override
    public void add(T t) {
        map.put(t, map.getOrDefault(t, 0) + 1);
        lifeTimeMap.put(t, lifeTimeMap.getOrDefault(t, 0) + 1);
    }

    //Возвращает количество добавлений данного элемента
    @Override
    public int getCount(T t) {
        if (lifeTimeMap.containsKey(t)) {
            return lifeTimeMap.get(t);
        } else {
            return 0;
        }
    }

    //Удаляет элемент и контейнера и возвращает количество его добавлений(до удаления)
    @Override
    public int remove(T t) {
        map.remove(t);
        return getCount(t);
    }

    //количество разных элементов
    @Override
    public int size() {
        return map.keySet().size();
    }

    //Добавить все элементы из source в текущий контейнер,
    // при совпадении ключей,     суммировать значения
    @Override
    public void addAll(CountMap source) {
        Map sourceMap = source.toMap();
        for ( Object o : sourceMap.keySet()) {
            if (map.containsKey(o)) {
                increaseCount((T) o, (Integer) sourceMap.get(o));
            } else {
                add((T) o);
            }
        }
    }

    private void increaseCount(T t, Integer count) {
        map.put(t, map.get(t) + count);
        lifeTimeMap.put(t, lifeTimeMap.get(t) + count);
    }

    //Вернуть java.util.Map. ключ - добавленный элемент,
    // значение - количество его добавлений
    @Override
    public Map toMap() {
        return map.keySet()
                .stream()
                .collect(Collectors.toMap(t -> t, lifeTimeMap::get, (a, b) -> b));
    }

    //Тот же самый контракт как и toMap(), только всю информацию записать в destination
    @Override
    public void toMap(Map destination) {
        destination.putAll(toMap());
    }
}

interface CountMap<T> {
    // добавляет элемент в этот контейнер.
    void add(T t);

    //Возвращает количество добавлений данного элемента
    int getCount(T t);

    //Удаляет элемент и контейнера и возвращает количество его добавлений(до удаления)
    int remove(T t);

    //количество разных элементов
    int size();

    //Добавить все элементы из source в текущий контейнер,
    // при совпадении ключей,     суммировать значения
    void addAll(CountMap source);

    //Вернуть java.util.Map. ключ - добавленный элемент,
    // значение - количество его добавлений
    Map toMap();

    //Тот же самый контракт как и toMap(), только всю информацию записать в destination
    void toMap(Map destination);
}

