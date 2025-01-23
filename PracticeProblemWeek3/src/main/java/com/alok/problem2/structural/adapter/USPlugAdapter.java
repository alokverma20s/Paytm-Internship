package com.alok.problem2.structural.adapter;

public class USPlugAdapter implements USPlug {
    private final EuropeanPlug europeanPlug;

    public USPlugAdapter(EuropeanPlug europeanPlug) {
        this.europeanPlug = europeanPlug;
    }

    public String connect() {
        return europeanPlug.connect() + " using US adapter";
    }
}
