package edu.sber.lect3;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;



public class CountMapImpl<T> implements CountMap<T> {

    private final Set<T> elements = new HashSet<>();
    private final Map<T, Integer> lifeTimeMap = new HashMap<>();

    // добавляет элемент в этот контейнер.
    @Override
    public void add(T t) {
        elements.add(t);
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
        elements.remove(t);
        return getCount(t);
    }

    //количество разных элементов
    @Override
    public int size() {
        return elements.size();
    }

    //Добавить все элементы из source в текущий контейнер,
    // при совпадении ключей,     суммировать значения
    @Override
    public void addAll(CountMap source) {
        Map sourceMap = source.toMap();
        for (Object o : sourceMap.keySet()) {
            elements.add((T) o);
            addOrIncreaseLifeTimeCount((T) o, (Integer) sourceMap.get(o));
        }
    }

    private void addOrIncreaseLifeTimeCount(T t, Integer count) {
        if (lifeTimeMap.containsKey(t)) {
            lifeTimeMap.replace(t, lifeTimeMap.get(t) + count);
        } else {
            lifeTimeMap.put(t, count);
        }
    }

    //Вернуть java.util.Map. ключ - добавленный элемент,
    // значение - количество его добавлений
    @Override
    public Map toMap() {
        return elements.stream()
                .collect(Collectors.toMap(e -> e, lifeTimeMap::get, (k, v) -> v));
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

