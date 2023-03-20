package org.example.hw1.task3;

import java.util.Arrays;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        List<Shape> shapes = Arrays.asList(new Triangle(), new Circle(), new Square());
        shapes.forEach(s -> s.draw());
    }
}
