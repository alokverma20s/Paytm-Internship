package com.alok.problem2.behavioral.command;

public class LightOnCommand implements Command {
    private final Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    public String execute() {
        return light.turnOn();
    }
}
