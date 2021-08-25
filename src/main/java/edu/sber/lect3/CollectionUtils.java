package edu.sber.lect3;

import java.util.*;

public class CollectionUtils {

    public static <T> void addAll(List<? extends T> source, List<? super T> destination) {
        destination.addAll(source);
    }

    public static <T> List newArrayList() {
        return new ArrayList<T>();
    }

    public static <T> int indexOf(List<? extends T> source, T t) {
        return source.indexOf(t);
    }

    public static <T> List limit(List<? extends T> source, int size) {
        if (source.size() > size) {
            return source.subList(0, size - 1);
        } else {
            return source;
        }
    }

    public static <T> void add(List<? super T> source, T t) {
        source.add(t);
    }

    public static <T> void removeAll(List<? super T> removeFrom, List<? extends T> c2) {
        removeFrom.removeAll(c2);
    }

    public static <T> boolean containsAll(List<? extends T> c1, List<? extends T> c2) {
        return c1.containsAll(c2);
    }

    public static <T> boolean containsAny(List<? extends T> c1, List<? extends T> c2) {
        for (T t : c2) {
            if (c1.contains(t)) {
                return true;
            }
        }
        return false;
    }

    public static <T extends Comparable<T>> List range(List<? extends T> list, Object min, Object max) {
        Collections.sort(list);
        int minElementPos = -1;
        int maxElementPos = -1;
        for (int i = 0; i < list.size() - 1; i++) {
            if ((list.get(i).compareTo((T) min) == 0) && (minElementPos == -1)) {
                minElementPos = i;
            }
            if (list.get(i).compareTo((T) max) == 0){
                maxElementPos = i;
            }
            if (list.get(i).compareTo((T) max) == 1) {
                break;
            }
        }
        return list.subList(minElementPos, maxElementPos);
    }

    public static <T extends Comparable<T>> List range(List<? extends T> list, Object min, Object max, Comparator comparator) {

    }
}
