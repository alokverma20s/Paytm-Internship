package com.alok.behaviourDesignPattern.commandDesignPattern;

public class AirConditioner {
    boolean isOn;
    int temperature;

    public void turnOnAC(){
        if(!isOn) {
            isOn = true;
            System.out.println("AC is ON");
        }
    }

    public void turnOffAC(){
        if(isOn) {
            isOn = false;
            System.out.println("AC is OFF");
        }
    }
    public void setTemperature(int temp){
        this.temperature = temp;
        System.out.println("Temperature change to " + temperature);
    }
}
