package edu.sber.lect4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.Scanner;

public class URLReader {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        URLReader urlReader = new URLReader();
        while (true) {
            System.out.println("type address:");
            urlReader.readContent(scanner.next());
        }
    }

    public void readContent(String target) {
        URLConnection urlConnection = null;
        try {
            urlConnection = new URL(target).openConnection();
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(urlConnection.getInputStream()));
            while ((bufferedReader.readLine()) != null) {
                System.out.println(bufferedReader.readLine());
            }
            System.exit(0);
        } catch (MalformedURLException e) {
            System.out.println("syntax error");
        } catch (UnknownHostException e) {
            System.out.println("unreachable host");
        } catch (ConnectException e) {
            System.out.println("connection problem");
        } catch (IOException e) {
            System.out.println("read error");
        }
    }

}
