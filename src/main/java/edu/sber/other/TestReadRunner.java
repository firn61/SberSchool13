package edu.sber.other;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class TestReadRunner {
    public static void main(String[] args) {
        String dir = "D:/java/";
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(dir + "test"))){
            Object o = (SerializableTest)ois.readObject();
            System.out.println(o);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
