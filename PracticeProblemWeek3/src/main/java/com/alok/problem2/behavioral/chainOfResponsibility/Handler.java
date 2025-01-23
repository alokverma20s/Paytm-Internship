package com.alok.problem2.behavioral.chainOfResponsibility;

// Chain of Responsibility Design Pattern
public abstract class Handler {
    protected Handler successor;

    public void setSuccessor(Handler successor) {
        this.successor = successor;
    }

    public abstract String handle(String request);
}
