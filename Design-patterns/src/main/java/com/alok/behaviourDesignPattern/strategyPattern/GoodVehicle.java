package com.alok.behaviourDesignPattern.strategyPattern;

import com.alok.behaviourDesignPattern.strategyPattern.strategy.NormalDriveStrategy;

public class GoodVehicle extends Vehicle {
    GoodVehicle(){
        super(new NormalDriveStrategy());
    }
}

