package edu.sber.lect1;

import org.junit.Test;

import static org.junit.Assert.*;

public class BubbleSorterTest {

    @Test
    public void sortCase1() {
        assertArrayEquals(new int[]{0, 1, 3, 4, 6, 7, 7, 7, 8, 11, 12, 13, 14, 16, 16, 17, 17, 17, 18, 18},
                BubbleSorter.sort(new int[]{0, 13, 14, 7, 17, 6, 12, 17, 11, 18, 7, 16, 4, 18, 7, 3, 16, 8, 17, 1}));
    }

    @Test
    public void sortCase2(){
        assertArrayEquals(new int[]{1,2,3,4},
                BubbleSorter.sort(new int[]{4,3,1,2}));
    }

}