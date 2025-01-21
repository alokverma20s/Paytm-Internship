package com.alok.abstractFactory;

public class EconomyCarFactory implements AbstractFactory{
    @Override
    public Car getInstance(int price) {
        if(price <= 300000){
            return new EconomyCar1();
        }
        else return new EconomyCar2();
    }
}
