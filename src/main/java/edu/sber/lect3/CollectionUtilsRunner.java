package edu.sber.lect3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class CollectionUtilsRunner {

    public static void main(String[] args) {

        List<String> list  = new ArrayList<>(Arrays.asList("str1", "str2", "str3"));
        CollectionUtils.add(list, 1);
        System.out.println(list);

    }

}
