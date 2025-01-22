package com.alok.behaviourDesignPattern.stateDesignPattern;

public class StopState implements State{
    @Override
    public void doAction(Context context) {
        System.out.println("Player is in stop State.");
        context.setState(this);
    }

    @Override
    public String toString() {
        return "Stop State";
    }
}
