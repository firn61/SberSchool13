package edu.sber.lect3;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class CountMapImplTest {

    CountMap<String> cMap = new CountMapImpl<>();
    CountMap<String> cMap2 = new CountMapImpl<>();


    public void initcMap() {

        cMap.add("hello"); //x3
        cMap.add("hello");
        cMap.add("hello");
        cMap.add("world"); //x2
        cMap.add("world");
        cMap.add("bye");   //x1

    }

    public void initcMap2(){

        cMap2.add("world");//x3
        cMap2.add("world");
        cMap2.add("world");
        cMap2.add("hello");//x2
        cMap2.add("hello");
        cMap2.add("another string");  //x1

    }

    @Test
    public void addCase1(){

        cMap.add("hello");
        cMap.add("hello");
        cMap.add("world");

        assertEquals(new HashMap(){{
            put("hello", 2);
            put("world", 1);
        }}, cMap.toMap());

    }

    @Test
    public void addCase2() {

        initcMap();
        cMap.add("hello");

        assertEquals(new HashMap() {{
            put("hello", 4);
            put("world", 2);
            put("bye", 1);
        }}, cMap.toMap());

    }


    @Test
    public void getCountCase1() {

        assertEquals(0, cMap.getCount("some string"));

    }

    @Test
    public void getCountCase2() {

        initcMap();
        assertEquals(3, cMap.getCount("hello"));

    }


    @Test
    public void removeCase1() {

        initcMap();
        assertEquals(3,cMap.remove("hello"));

    }

    @Test
    public void removeCase2() {

        initcMap();
        cMap.add("hello");
        cMap.remove("hello");
        cMap.remove("hello");
        assertEquals(4, cMap.remove("hello"));

    }

    @Test
    public void sizeCase1() {

        assertEquals(0, cMap.size());

    }

    @Test
    public void sizeCase2() {

        initcMap();
        assertEquals(3, cMap.size());

    }

    @Test
    public void addAllCase1() {

        initcMap();
        initcMap2();
        cMap.addAll(cMap2);
        assertEquals(new HashMap(){{
            put("hello", 5);
            put("world", 5);
            put("bye", 1);
            put("another string", 1);
        }}, cMap.toMap());

    }

    @Test
    public void toMapCase1() {

        initcMap();
        assertEquals(new HashMap(){{
            put("hello", 3);
            put("world", 2);
            put("bye", 1);
        }}, cMap.toMap());

    }

    @Test
    public void toVoidMapCase1(){

        Map<String, Integer> destination = new HashMap<>();
        initcMap();
        cMap.toMap(destination);
        cMap.remove("hello");
        assertEquals(new HashMap(){{
            put("hello", 3);
            put("world", 2);
            put("bye", 1);
        }}, destination);

    }
}