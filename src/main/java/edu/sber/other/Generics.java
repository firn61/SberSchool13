package edu.sber.other;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Generics {


    public static void main(String[] args) {

        Generics g = new Generics();
        List<? extends A> lsa = new ArrayList<>();

        List<A> la = new ArrayList<>();
        la.add(new A());
        la.add(new A());
        la.add(new A());
        la.add(new B());
        la.add(new C());
        lsa = la;
        Stream.of(Object.class.getDeclaredMethods()).forEach(System.out::println);

    }
}

class A {
    @Override
    public String toString() {
        return "A{" + hashCode() + "}";
    }
}

class B extends A {
    @Override
    public String toString() {
        return "B{" + hashCode() + "}";
    }
}

class C extends B {
    @Override
    public String toString() {
        return "C{" + hashCode() + "}";
    }
}
