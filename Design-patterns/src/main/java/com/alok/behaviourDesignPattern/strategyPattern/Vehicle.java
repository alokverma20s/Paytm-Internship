package com.alok.behaviourDesignPattern.strategyPattern;

import com.alok.behaviourDesignPattern.strategyPattern.strategy.DriveStrategy;

public class Vehicle{
    DriveStrategy driveStrategy;

    // This is known as constructor injection;
    Vehicle(DriveStrategy driveObj){
        this.driveStrategy = driveObj;
    }
    public void drive(){
        this.driveStrategy.drive();
    }
}
