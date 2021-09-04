package edu.sber.lect6;

import java.util.Iterator;

public class ObjectArrayIteratorRunner {
    public static void main(String[] args) {
        Object[] array = new Object[]{
                new Object(),
                new Object(),
                new Object(),
                new Object(),
                new Object(),
                new Object(),
                new Object(),
                new Object(),
                new Object(),
                new Object()};
        Iterator<Object> objectIterator = new ObjectArrayIterator<>(array);
        int i = 0;
        while (objectIterator.hasNext()){
            System.out.println("hasnext " + ++i + objectIterator.next());
        }
    }
}
