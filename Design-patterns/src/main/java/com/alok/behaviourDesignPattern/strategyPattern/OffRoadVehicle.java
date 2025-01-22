package com.alok.behaviourDesignPattern.strategyPattern;

import com.alok.behaviourDesignPattern.strategyPattern.strategy.SportDriveStrategy;

public class OffRoadVehicle extends  Vehicle{
    OffRoadVehicle(){
        super(new SportDriveStrategy());
    }
}

