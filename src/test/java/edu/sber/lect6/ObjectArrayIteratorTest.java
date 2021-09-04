package edu.sber.lect6;

import static org.mockito.Mockito.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class ObjectArrayIteratorTest {

    @Test
    public void hasNext1() {
        ObjectArrayIterator i = mock(ObjectArrayIterator.class);
        when(i.next()).thenReturn("iter1").thenReturn("iter2").thenReturn("iter3");
        String result1 = i.next()+ " " + i.next() + " " + i.next();
        assertEquals("iter1 iter2 iter3", result1);
    }

    @Test
    public void hasNext2(){
        Object[] arr = new Object[]{"iter", "iter", "iter"};
        ObjectArrayIterator i = new ObjectArrayIterator(arr);
        i.next();
        i.next();
        i.next();
        i.next();
        assertNull(i.next());
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void hasNext3(){
        ObjectArrayIterator i = new ObjectArrayIterator(new Object[]{"iter1", "iter2"});
        i.next();
        i.previous();
        i.previous();
        i.previous();

    }

    @Test
    public void hasNext(){
        ObjectArrayIterator<String> i =  mock(ObjectArrayIterator.class);
        i.hasNext();
        i.next();
        verify(i).next();
    }

    @Test
    public void next1() {
        ObjectArrayIterator<String> i = new ObjectArrayIterator<>(new Object[]{"iter1", "iter2"});
        assertTrue(i.hasNext());
    }

    @Test
    public void next2() {
        ObjectArrayIterator<String> i = new ObjectArrayIterator<>(new Object[]{});
        i.next();
        assertFalse(i.hasNext());
    }
}