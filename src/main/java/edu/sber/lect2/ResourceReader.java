package edu.sber.lect2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ResourceReader {
    List<String> read(String file, int rowNumLimiter) {
        int rowsReaded = 0;
        List<String> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(this.getClass()
                .getResource("/" + file).getPath()))) {
            String line;
            while (((line = reader.readLine()) != null) && (++rowsReaded <=rowNumLimiter )) {
                result.add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
