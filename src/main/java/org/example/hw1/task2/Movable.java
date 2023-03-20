package org.example.hw1.task2;

public interface Movable {
    void move();
}

interface Stopable {
    void stop();
}

class Engine {
    private int power;

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }
}

abstract class Car {
    public Engine engine;
    private String color;
    private String name;
    protected void start() {
        System.out.println("Car starting");
    }
    abstract void open();
    public Engine getEngine() {
        return engine;
    }
    public void setEngine(Engine engine) {
        this.engine = engine;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}

class LightWeightCar extends Car implements Movable {
    @Override
    void open() {
        System.out.println("Car is open");
    }
    @Override
    public void move() {
        System.out.println("Car is moving");
    }
}

class Lorry extends Car implements Movable, Stopable {
    public void move(){
        System.out.println("Car is moving");
    }

    @Override
    public void stop(){
        System.out.println("Car is stop");
    }

    @Override
    void open() {

    }
}
