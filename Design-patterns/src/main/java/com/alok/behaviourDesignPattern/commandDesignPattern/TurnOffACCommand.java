package com.alok.behaviourDesignPattern.commandDesignPattern;

public class TurnOffACCommand implements ICommand{

    private AirConditioner ac;

    public TurnOffACCommand(AirConditioner ac){
        this.ac = ac;
    }

    @Override
    public void execute() {
        ac.turnOffAC();
    }

    @Override
    public void undo() {
        ac.turnOnAC();
    }
}
