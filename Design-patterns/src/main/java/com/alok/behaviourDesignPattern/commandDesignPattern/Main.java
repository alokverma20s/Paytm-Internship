package com.alok.behaviourDesignPattern.commandDesignPattern;

public class Main {
    public static void main(String[] args){
        AirConditioner airConditioner = new AirConditioner();

        MyRemoteControl myRemoteControl = new MyRemoteControl();

        myRemoteControl.setCommand(new TurnOnACCommand(airConditioner));
        myRemoteControl.pressButton();
        myRemoteControl.pressButton();
        myRemoteControl.pressButton();
        myRemoteControl.pressButton();
        myRemoteControl.undo();
        myRemoteControl.undo();
        myRemoteControl.undo();
        myRemoteControl.undo();
//        myRemoteControl.setCommand(new TurnOffACCommand(airConditioner));
//        myRemoteControl.pressButton();
    }
}
