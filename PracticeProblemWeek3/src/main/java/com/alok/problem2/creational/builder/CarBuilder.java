package com.alok.problem2.creational.builder;

public class CarBuilder {
    private final Car car = new Car();

    public CarBuilder addWheels() {
        car.addPart("Wheels");
        return this;
    }

    public CarBuilder addEngine() {
        car.addPart("Engine");
        return this;
    }

    public CarBuilder addBody() {
        car.addPart("Body");
        return this;
    }

    public Car build() {
        return car;
    }
}
