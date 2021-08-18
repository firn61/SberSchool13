package edu.sber.lect1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Hierarchy {

    public static void main(String[] args) {
        Square square = new Square(2);
        Square rect = new Rect(2, 3);
        Circle circle = new Circle(4);
        Shape triangle = new Triangle(1, 2, 3);
        List<Shape> list = Arrays.asList(square, rect, circle, triangle);
        for (Shape shape : list) {
            System.out.println(shape.getClass().getName() + " area: "
                    + shape.area() + " perimeter: " + shape.perimeter());
        }
    }
}

interface Shape {

    double area();

    double perimeter();

}

class Square implements Shape {

    int a;

    public Square(int a) {
        this.a = a;
    }

    @Override
    public double area() {
        return a * a;
    }

    @Override
    public double perimeter() {
        return 4 * a;
    }
}

class Rect extends Square {

    int b;

    public Rect(int a, int b) {
        super(a);
        this.b = b;
    }

    @Override
    public double area() {
        return a * b;
    }

    @Override
    public double perimeter() {
        return 2 * (a + b);
    }
}

class Circle implements Shape {

    int r;

    public Circle(int r) {
        this.r = r;
    }

    @Override
    public double area() {
        return Math.PI * r * r;
    }

    @Override
    public double perimeter() {
        return 2 * Math.PI * r;
    }
}

class Triangle implements Shape {

    int a;
    int b;
    int c;

    public Triangle(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public double area() {
        return (c * c + b * b - a * a) / (2 * a * a); // но это не точно
    }

    @Override
    public double perimeter() {
        return a + b + c;
    }
}
