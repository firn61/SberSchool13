package edu.sber.other;

import java.util.ArrayList;
import java.util.List;

public class Generics {

    public static void main(String[] args) {
    MyClass1 mc11 = new MyClass1(1);
    MyClass1 mc12 = new MyClass1(1);
    MyClass1 mc21 = new MyClass2(1);
    MyClass2 mc22 = new MyClass2(1);

    List<MyClass1> list = new ArrayList<>();
        list.add(mc11);
        list.add(mc22);

    }


}

class MyClass1 {
    int i;

    public MyClass1(int i) {
        this.i = i;
    }

    public String getI() {
        return "MyClass1 i: " + i;
    }
}

class MyClass2 extends MyClass1{

    public MyClass2(int i) {
        super(i);
    }

    @Override
    public String getI() {
        return "MyClass1 i: " + i;
    }
}