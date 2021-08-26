package edu.sber.lect3;

import java.util.*;

public class CollectionUtils {

    public static <T> void addAll(List<? extends T> source, List<? super T> destination) {
        destination.addAll(source);
    }

    public static <T> List<T> newArrayList() {
        return new ArrayList<>();
    }

    public static <T> int indexOf(List<? extends T> source, Object o) {
        return source.indexOf((T) o);
    }

    public static <T> List<? extends T> limit(List<? extends T> source, int size) {
        System.out.println(size);
        // return source.size() > size ? source.subList(0, size - 1) : source;
        return new ArrayList<>(source.subList(0, size));
    }

    public static <T> void add(List<? super T> source, Object o) {
        source.add((T) o);
    }

    public static <T> void removeAll(List<? super T> removeFrom, List<? extends T> c2) {
        removeFrom.removeAll(c2);
    }

    public static <T> boolean containsAll(List<? super T> c1, List<? extends T> c2) {
        return c1.containsAll(c2);
    }

    public static <T> boolean containsAny(List<? super T> c1, List<? extends T> c2) {
        return c2.stream().anyMatch(c1::contains);
    }

    public static <T extends Comparable<T>> List<T> range(List<? extends T> list, Object min, Object max) {
        Collections.sort(list);
        List<T> result = new ArrayList<>();
        for (T t : list) {
            if ((t.compareTo((T) min) >= 0) && (t.compareTo((T) max) <= 0)) {
                add(result, t);
            }
        }
        return result;
    }

    public static <T extends Comparable<T>> List range(List<? extends T> list, Object min, Object max, Comparator comparator) {
        List<T> result = new ArrayList<>();
        Collections.sort(list, comparator);
        for (T t : list) {
            if ((t.compareTo((T) min) >= 0) && (t.compareTo((T) max) <= 0)) {
                add(result, t);
            }
        }
        return result;
    }

}
class CollectionUtilComparator<T extends Comparable<T>> implements Comparator<T>{
    @Override
    public int compare(T o1, T o2) {
        return o1.compareTo(o2);
    }
}
