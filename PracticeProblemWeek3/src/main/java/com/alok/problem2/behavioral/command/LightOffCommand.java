package com.alok.problem2.behavioral.command;

public class LightOffCommand implements Command {
    private final Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    public String execute() {
        return light.turnOff();
    }
}
