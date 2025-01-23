package com.alok.problem2.creational.builder;

import java.util.ArrayList;
import java.util.List;

// Builder Design Pattern
public class Car {
    private final List<String> parts = new ArrayList<>();

    public void addPart(String part) {
        parts.add(part);
    }

    public List<String> showParts() {
        return parts;
    }
}
