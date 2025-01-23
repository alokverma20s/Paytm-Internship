package com.alok.problem3;

public class Car {
    private String brand;
    private String model;
    private int price;

    public String DisplayDetails() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", price=" + price +
                '}';
    }
}
