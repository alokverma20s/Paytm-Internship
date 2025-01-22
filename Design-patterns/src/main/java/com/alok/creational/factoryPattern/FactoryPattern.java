package com.alok.creational.factoryPattern;


// It is used when all the object creation and its business logic we need to keep at one place

public class FactoryPattern {
    public static void main(String args[]){
        ShapeInstanceFactory shapeInstanceFactory = new ShapeInstanceFactory();
        Shape circleObj = shapeInstanceFactory.getInstance("Circle");
        circleObj.compute();
    }
}
