package edu.sber.lect1;

import java.util.Random;

public class BinarySearch {

    public static void main(String[] args) {

        int arrSize = 20;

        int[] arr = new int[arrSize];
        for (int i = 0; i < arrSize; i++) {
            arr[i] = new Random().nextInt(arrSize);
        }
        //Arrays.sort(arr);
        BubbleSorter.sort(arr);
        System.out.println("position: " + search(arr, new Random().nextInt(arrSize)));
    }

    static int search(int[] arr, int value) {

        int result = -1;
        int lowCap = 0;
        int highCap = arr.length;
        int steps = 0;
        System.out.println("value: " + value);
        while (lowCap <= highCap) {
            steps++;
            int middle = lowCap + ((highCap - lowCap) / 2);
            int middleValue = arr[middle];
            System.out.println("low: " + lowCap + "  mid: " + middle + " high: "
                    + highCap + " value: " + middleValue);
            if (middleValue == value) {
                System.out.println("steps: " + steps);
                return middle;
            } else if (middleValue > value) {
                highCap = middle - 1;
            } else if (middleValue < value) {
                lowCap = middle + 1;
            }
        }
        System.out.println("steps: " + steps);
        return result;
    }

}
