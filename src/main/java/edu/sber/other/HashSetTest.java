package edu.sber.other;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class HashSetTest {
    public static void main(String[] args) {
        Set<MyClass> set = new HashSet<>();
        set.add(new MyClass(1,"1"));
        set.add(new MyClass(1, "2"));
        set.add(new MyClass(1, "2"));
        for (MyClass myClass : set) {
            System.out.println(myClass + " hash: " + myClass.hashCode());
        }
    }
}

class MyClass {
    Integer id;
    String name;

    public MyClass(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "MyClass{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyClass myClass = (MyClass) o;
        return Objects.equals(id, myClass.id) && Objects.equals(name, myClass.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}