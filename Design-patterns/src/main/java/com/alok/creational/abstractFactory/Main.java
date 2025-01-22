package com.alok.creational.abstractFactory;

public class Main {
    public static void main(String args[]){
        AbstactFactoryProducer abstactFactoryProducer = new AbstactFactoryProducer();
        AbstractFactory abstractFactory = abstactFactoryProducer.getFactoryInstance("Premium");
        Car carObj = abstractFactory.getInstance(4000000);
        System.out.println(carObj.getTopSpeed());
    }
}
