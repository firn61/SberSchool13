package edu.sber.lect3;

import java.util.Map;

public class CountMapImpl implements CountMap{

    // добавляет элемент в этот контейнер.
    @Override
    public void add(Object o) {

    }

    //Возвращает количество добавлений данного элемента
    @Override
    public int getCount(Object o) {
        return 0;
    }

    //Удаляет элемент и контейнера и возвращает количество его добавлений(до удаления)
    @Override
    public int remove(Object o) {
        return 0;
    }

    //количество разных элементов
    @Override
    public int size() {
        return 0;
    }

    //Добавить все элементы из source в текущий контейнер,
    // при совпадении ключей,     суммировать значения
    @Override
    public void addAll(CountMap source) {

    }

    //Вернуть java.util.Map. ключ - добавленный элемент,
    // значение - количество его добавлений

    @Override
    public Map toMap() {
        return null;
    }

    //Тот же самый контракт как и toMap(), только всю информацию записать в destination
    @Override
    public void toMap(Map destination) {

    }
}
