package edu.sber.lect2;

import java.util.Iterator;
import java.util.List;

class ReverseIterator<E> implements Iterator<E> {

    List<E> list;
    int currentPosition;

    public ReverseIterator(List<E> list) {
        this.list = list;
        currentPosition = list.size()-1;
    }

    public E previous() {
        return list.get(currentPosition--);
    }

    public boolean hasPrevious() {
        return (currentPosition >= 0);
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public E next() {
        return null;
    }
}