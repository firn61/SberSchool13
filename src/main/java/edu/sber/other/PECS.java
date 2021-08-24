package edu.sber.other;

import java.util.ArrayList;
import java.util.List;

public class PECS {

    public static void main(String[] args) {
        List<? super A> lsa = new ArrayList<>();
        lsa.add(new A());
        lsa.add(new B());
        lsa.add(new C());
        for (Object b: lsa) {
            System.out.println(b);
        }
    }
}

//class A {
//    @Override
//    public String toString() {
//        return "A{}";
//    }
//}
//class B extends A{
//    @Override
//    public String toString() {
//        return "B{}";
//    }
//}
//class C extends B{
//    @Override
//    public String toString() {
//        return "C{}";
//    }
//}

