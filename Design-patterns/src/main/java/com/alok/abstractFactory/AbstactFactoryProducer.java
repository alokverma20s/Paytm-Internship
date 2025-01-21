package com.alok.abstractFactory;
// It's a factory of factory

public class AbstactFactoryProducer {
    public AbstractFactory getFactoryInstance(String value){
        if(value.equals("Economic")){
            return new EconomyCarFactory();
        }
        else if(value.equals("Luxury") || value.equals("Premium")){
            return new LuxaryCarFactory();
        }
        return null;
    }
}
