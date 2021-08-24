package edu.sber.lect2;

import java.util.*;

public class WordTasks {

    public static void main(String[] args) {

        int rowNum = 10;

        List<String> sourceList = new ResourceReader().read("TextExample", rowNum);
        System.out.println("case1: " + case1(sourceList));

        System.out.println("case2 output: ");
        for (String s : case2(sourceList)) {
            System.out.println(s);
        }

        System.out.println("case3 output: ");
        for (Map.Entry<String, Integer> stringIntegerEntry : case3(sourceList).entrySet()) {
            System.out.println(stringIntegerEntry.getKey() + " > " + stringIntegerEntry.getValue());
        }

        System.out.println("case4 output: ");
        for (String s : case4(sourceList)) {
            System.out.println(s);
        }

        System.out.println("case5 output: ");
        case5(sourceList);

        System.out.println("case6 output: ");
        for (int i = 0; i < 5; i++) {
            System.out.println("Input string num: ");
            Scanner scanner = new Scanner(System.in);
            System.out.println(case6(sourceList, scanner.nextInt()));
        }
    }

    //Задание 1: Подсчитайте количество различных слов в файле.
    static int case1(List list) {
       // list.stream().distinct().count();
        return new HashSet(list).size();
    }

    //Задание 2: Выведите на экран список различных слов файла, отсортированный по возрастанию их длины
    // (компаратор сначала по длине слова, потом по тексту).
    static Set<String> case2(List list) {
        TreeSet<String> result = new TreeSet<>((o1, o2) -> {
            if (o1.length() > o2.length()) {
                return 1;
            } else if (o1.length() < o2.length()) {
                return -1;
            } else {
                return o1.compareTo(o2);
            }
        });
        result.addAll(list);
        return result;
    }

    //Задание 3: Подсчитайте и выведите на экран сколько раз каждое слово встречается в файле.
    static Map<String, Integer> case3(List<String> list) {
        Map<String, Integer> result = new HashMap<>();
        for (String us : new HashSet<>(list)) {
            result.put(us, (int) list.stream()
                    .filter(s -> s.equals(us))
                    .count());
        }
        return result;
    }

    //Задание 4: Выведите на экран все строки файла в обратном порядке.
    static List<String> case4(List list) {
        List<String> result = new ArrayList<>();
        result.addAll(list);
        Collections.reverse(result);
        return result;
    }

    //Задание 5: Реализуйте свой Iterator для обхода списка в обратном порядке.
    static void case5(List list) {
        ReverseIterator<String> iter = new ReverseIterator<>(list);
        while (iter.hasPrevious()) {
            System.out.println(iter.previous());
        }
    }

    //Задание 6: Выведите на экран строки, номера которых задаются пользователем в произвольном порядке.
    static String case6(List<String> list, int stringNum) {
        return list.get(stringNum - 1);
    }

}

