package com.alok.behaviourDesignPattern.strategyPattern;

import com.alok.behaviourDesignPattern.strategyPattern.strategy.SportDriveStrategy;

public class SportVehicle extends Vehicle{
    SportVehicle(){
        super(new SportDriveStrategy());
    }
}
