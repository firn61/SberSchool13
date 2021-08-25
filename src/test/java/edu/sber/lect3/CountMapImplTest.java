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

        assertEquals(cMap.toMap(), new HashMap(){{
            put("hello", 2);
            put("world", 1);
        }});

    }

    @Test
    public void addCase2() {

        initcMap();
        cMap.add("hello");

        assertEquals(cMap.toMap(), new HashMap() {{
            put("hello", 4);
            put("world", 2);
            put("bye", 1);
        }});

    }


    @Test
    public void getCountCase1() {

        assertEquals(cMap.getCount("some string"), 0);

    }

    @Test
    public void getCountCase2() {

        initcMap();
        assertEquals(cMap.getCount("hello"), 3);

    }


    @Test
    public void removeCase1() {

        initcMap();
        assertEquals(cMap.remove("hello"), 3);

    }

    @Test
    public void removeCase2() {

        initcMap();
        cMap.add("hello");
        cMap.remove("hello");
        cMap.remove("hello");
        assertEquals(cMap.remove("hello"), 4);

    }

    @Test
    public void sizeCase1() {

        assertEquals(cMap.size(), 0);

    }

    @Test
    public void sizeCase2() {

        initcMap();
        assertEquals(cMap.size(), 3);

    }

    @Test
    public void addAllCase1() {

        initcMap();
        initcMap2();
        cMap.addAll(cMap2);
        assertEquals(cMap.toMap(), new HashMap(){{
            put("hello", 5);
            put("world", 5);
            put("bye", 1);
            put("another string", 1);
        }});

    }

    @Test
    public void toMapCase1() {

        initcMap();
        assertEquals(cMap.toMap(), new HashMap(){{
            put("hello", 3);
            put("world", 2);
            put("bye", 1);
        }});

    }

    @Test
    public void toVoidMapCase1(){

        Map<String, Integer> destination = new HashMap<>();
        initcMap();
        cMap.toMap(destination);
        cMap.remove("hello");
        assertEquals(destination, new HashMap(){{
            put("hello", 3);
            put("world", 2);
            put("bye", 1);
        }});

    }
}