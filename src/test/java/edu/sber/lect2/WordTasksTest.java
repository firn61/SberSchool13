package edu.sber.lect2;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class WordTasksTest {

    @Test
    public void case1() {
        assertEquals(4,
                WordTasks.case1(List.of("String1", "String2", "String3", "String4", "String4")));
    }

    @Test
    public void case2() {
        assertEquals(Set.of("A", "B", "C", "AA", "AB", "CC"),
                WordTasks.case2(List.of("B", "CC", "A", "C", "AA", "AB")));
    }

    @Test
    public void case3() {
        assertEquals(Map.of("String1", 1,
                "String2", 2,
                "String3", 3),
                WordTasks.case3(List.of("String1", "String2", "String2", "String3", "String3", "String3")));
    }

    @Test
    public void case4() {
        assertEquals(List.of("1st", "2nd", "3rd", "4th"),
                WordTasks.case4(List.of("4th", "3rd", "2nd", "1st")));
    }
}