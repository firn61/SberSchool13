package edu.sber.lect5;

import java.lang.reflect.Field;

public class StringConstants {

    public static final String MONDAY = "MONDAY";
    public static final String TUESDAY = "TUESDAY";
    public static final String WEDNESDAY = "WEDNESDAY";
    public static final String THURSDAY = "THURSDAY1";
    public static final String FRIDAY = "FRIDAY";
    public static final String SATURDAY = "SATURDAY";
    public static final String SUNDAY = "SUNDAY";

    public boolean checkFieldNamesAndValues(Object o) throws IllegalAccessException {
        Class<?> clazz = o.getClass();
        Field[] fields = clazz.getDeclaredFields();
        boolean discrepancy = false;
        for (Field field : fields) {
            if (!field.getName().equals(field.get(String.class))) {
                discrepancy = true;
                System.out.println("field: " + field.getName() + " <> " + " value: " +field.get(String.class));
            }
        }
        return discrepancy;
    }

    public static void main(String[] args) throws IllegalAccessException {
        StringConstants sc = new StringConstants();
        System.out.println(sc.checkFieldNamesAndValues(sc) ? "Имеются несоответствующие поля" : "Ок");
    }
}
