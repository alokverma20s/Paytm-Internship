package com.alok.factoryPattern;

public class ShapeInstanceFactory {
    public Shape getInstance(String value){
        if(value.equals("Circle")) return new Circle();
        else if(value.equals("Square")) return new Square();
        else if(value.equals("Rectangle")) return new Rectangle();
        return null;
    }
}
