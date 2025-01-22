package com.alok.behaviourDesignPattern.commandDesignPattern;

public class TurnOnACCommand implements ICommand{

    private AirConditioner ac;

    public TurnOnACCommand(AirConditioner ac){
        this.ac = ac;
    }

    @Override
    public void execute() {
        ac.turnOnAC();
    }

    @Override
    public void undo() {
        ac.turnOffAC();
    }
}
