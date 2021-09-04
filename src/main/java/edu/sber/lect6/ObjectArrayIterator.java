package edu.sber.lect6;

import java.util.Iterator;

public class ObjectArrayIterator<E> implements Iterator<E> {

    private final Object[] array;
    private final int size;
    private int position;

    public ObjectArrayIterator(Object[] array) {
        this.array = array;
        size = array.length;
    }

    @Override
    public boolean hasNext() {
        return position < size;


    }

    @Override
    public E next() {
        if (hasNext()) {
            return (E) array[position++];
        } else {
            return null;
        }
    }

    public E previous() {
        return (E) array[position--];
    }
}


