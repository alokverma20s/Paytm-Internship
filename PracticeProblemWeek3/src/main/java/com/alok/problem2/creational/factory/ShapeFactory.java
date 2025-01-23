package com.alok.problem2.creational.factory;

public class ShapeFactory {
    public static Shape getShape(String shapeType) {
        if (shapeType.equals("Circle")) {
            return new Circle();
        } else if (shapeType.equals("Square")) {
            return new Square();
        }
        return null;
    }
}
