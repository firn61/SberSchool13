package edu.sber.lect1;


import java.util.Arrays;

public class BubbleSorter {

    public static int[] sort(int[] arr){
        int iterations = 0;
        int switches = 0;
        boolean sorted = false;
        while (!sorted) {
            iterations++;
            sorted = true;
            System.out.println(Arrays.toString(arr));
            for (int i = 0; i < arr.length-1; i++) {
                if (arr[i] > arr[i+1]) {
                    int tempValue = arr[i];
                    arr[i] = arr[i+1];
                    arr[i+1] = tempValue;
                    switches++;
                    sorted = false;
                }
            }
        }
        System.out.println("sort iterations: " + iterations  + " switches: " + switches);
        return arr;
    }

}
