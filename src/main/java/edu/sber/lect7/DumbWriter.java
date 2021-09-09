package edu.sber.lect7;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class DumbWriter {
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
}}
