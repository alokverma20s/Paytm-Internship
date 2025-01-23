package com.alok.problem2.behavioral.state;

public class Context {
    private State state;

    public Context(State state) {
        this.state = state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String request() {
        return state.handle();
    }
}
