package edu.sber.lect3;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class CollectionUtilsTest {

    List<String> list1 = new ArrayList<>();
    List<String> list2 = new ArrayList<>();

    public void initList1() {

        list1.add("element0");
        list1.add("element1");
        list1.add("element2");
        list1.add("element3");
        list1.add("element4");
        list1.add("element5");
        list1.add("element6");
        list1.add("element7");
        list1.add("element8");
        list1.add("element9");

    }

    public void initList2() {

        list2.add("element1");
        list2.add("element5");
        list2.add("other element0");
        list2.add("other element1");
        list2.add("other element2");

    }

    @Test
    public void newArrayListCase1() {
        List<String> newArrayList1 = CollectionUtils.newArrayList();
        newArrayList1.add("element");
        assertEquals(new ArrayList<>(Arrays.asList("element")), newArrayList1);
    }

    @Test
    public void indexOfCase1() {
        initList1();
        assertEquals(5, CollectionUtils.indexOf(list1, "element5"));
    }

    @Test
    public void indexOfCase2() {
        initList1();
        assertEquals(-1, CollectionUtils.indexOf(list1, "another element"));
    }

    @Test
    public void limitcase1() {
        initList1();
        assertEquals(new ArrayList<>(Arrays.asList("element0", "element1", "element2")), CollectionUtils.limit(list1,3));
    }

    @Test
    public void limitcase2() {
        initList1();
        assertEquals(new ArrayList<>(Arrays.asList("element0")), CollectionUtils.limit(list1,1));
    }

    @Test
    public void limitcase3() {
        initList1();
        assertTrue(CollectionUtils.limit(list1,0).isEmpty());
    }

    @Test
    public void addCase1() {

        CollectionUtils.add(list1, "element");
        assertEquals(new ArrayList<>(Arrays.asList("element")), list1);

    }

    @Test
    public void addCase2() {

        CollectionUtils.add(list1, "element");
        CollectionUtils.add(list1, "element");
        CollectionUtils.add(list1, "other element");
        assertEquals(new ArrayList<>(Arrays.asList("element", "element", "other element")), list1);

    }

    @Test
    public void removeAllCase1() {

        initList1();
        initList2();
        CollectionUtils.removeAll(list1, list2);
        assertEquals(new ArrayList<>(Arrays.asList("element0", "element2", "element3", "element4", "element6",
                "element7", "element8", "element9")), list1);

    }

    @Test
    public void removeAllCase2() {

        initList1();
        CollectionUtils.removeAll(list1, new ArrayList<>(Arrays.asList("element0", "element1", "element2", "element3", "element4")));
        assertEquals(new ArrayList<>(Arrays.asList("element5", "element6", "element7", "element8", "element9")), list1);

    }

    @Test
    public void containsAllCase1() {

        initList1();
        initList2();
        assertFalse(CollectionUtils.containsAll(list1, list2));

    }

    @Test
    public void containsAllCase2() {

        initList1();
        assertTrue(CollectionUtils.containsAll(list1, new ArrayList<>(Arrays.asList("element0", "element9"))));

    }

    @Test
    public void containsAnyCase1() {

        initList1();
        initList2();
        assertTrue(CollectionUtils.containsAny(list1, list2));

    }

    @Test
    public void containsAnyCase2() {

        initList1();
        assertFalse(CollectionUtils.containsAny(list1, new ArrayList<>(Arrays.asList("other element0", "other element1"))));

    }

    @Test
    public void rangeWoCompCase1() {

        initList1();
        list1.remove("element3");
        Collections.shuffle(list1);
        assertEquals(new ArrayList<>(Arrays.asList("element2", "element4", "element5")), CollectionUtils.range(list1, "element2", "element5"));

    }

    @Test
    public void rangeWoCompCase2() {

        initList1();
        Collections.shuffle(list1);
        list1.add("element2");
        assertEquals(new ArrayList<>(Arrays.asList("element2", "element2")), CollectionUtils.range(list1, "element2", "element2"));

    }

    @Test
    public void testRangeCase1() {

        initList1();
        Collections.shuffle(list1);
        list1.add("element2");
        assertEquals(new ArrayList<>(Arrays.asList("element2", "element2")), CollectionUtils.range(list1, "element2", "element2", new CollectionUtilComparator()));

    }
}