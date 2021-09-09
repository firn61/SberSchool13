package edu.sber.lect7;

import java.util.Arrays;

public class DumbEncrypter {

    public static byte[] code(byte[] bytes) {
        byte[] result = Arrays.copyOf(bytes, bytes.length);
        for (int i = 0; i < result.length; i++) {
            result[i]++;
        }
        return result;
    }

    public static byte[] encode(byte[] bytes) {
        byte[] result = Arrays.copyOf(bytes, bytes.length);
        for (int i = 0; i < result.length; i++) {
            result[i]--;
        }
        return result;
    }

}
