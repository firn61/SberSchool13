package edu.sber.lect1;

import org.junit.Test;

import static org.junit.Assert.*;

public class BinarySearchTest {

    @Test
    public void searchCase1() {
        assertEquals(6, BinarySearch.search(new int[]{0, 0, 0, 1, 2, 3, 4, 6, 6, 7, 9, 9, 13, 14, 15, 15, 15, 17, 18, 18}, 4));
    }

    @Test
    public void searchCase2(){
        assertEquals(7, BinarySearch.search(new int[]{1, 2, 2, 3, 3, 3, 4, 5, 5, 5, 6, 7, 9, 10, 11, 13, 14, 15, 19, 19}, 5));
    }
}