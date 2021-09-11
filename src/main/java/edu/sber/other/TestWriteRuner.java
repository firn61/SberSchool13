package edu.sber.other;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class TestWriteRuner {

    public static void main(String[] args) {
        String dir = "D:/java/";
        SerializableTest st = new SerializableTest();
        st.setId(123L);
        st.setName("name");
        st.setText("text");
        String fileName = "test";
        //st.test = fileName;
        System.out.println(fileName);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(dir + fileName))) {
            oos.writeObject(st);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
