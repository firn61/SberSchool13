package edu.sber.lect8.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ServiceImpl implements Service{

    @Override
    public double doHardWork(String name, Double var) {
        System.out.println("Really Hard work with " + name);
        try {
            Thread.sleep(100 * var.longValue());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return var * 2;
    }

    @Override
    public List<String> run(String item, Double value, Date date) {
        System.out.println("Run with: " + item + " " +  value + " " +  date);
        List<String> result = new ArrayList<>();
        for (int i = 0; i < value; i++) {
            result.add(new StringBuilder(item + " " + date.toString()).toString());
        }
        return result;
    }

    @Override
    public List<String> work(String item) {
        System.out.println("Work with " + item);
        List<String> result = new ArrayList<>();
        int elementsCount = 1_000_000;
        try {
            for (int i = 0; i < elementsCount; i++) {
                result.add(item.toUpperCase(Locale.ROOT) + i);
            }
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }
}
