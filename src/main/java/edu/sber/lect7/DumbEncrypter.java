package edu.sber.lect7;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
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

    public static void writeFile(byte[] bytes, String path, String name){
        try {
            FileOutputStream fos = new FileOutputStream(path + "/" + name);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            bos.write(bytes);
            bos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        Path folder = Paths.get(path + "/" + name);
//        try (BufferedWriter writer = Files.newBufferedWriter(folder,
//                Charset.forName("UTF-8"), StandardOpenOption.CREATE_NEW)) {
//            for (int i = 0; i < bytes.length; i++) {
//                writer.write(bytes[i]);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
