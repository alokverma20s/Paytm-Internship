package com.alok.problem2.structural.decorator;

public class SugarDecorator extends Coffee {
    private final Coffee coffee;

    public SugarDecorator(Coffee coffee) {
        this.coffee = coffee;
    }

    public int cost() {
        return coffee.cost() + 1;
    }
}
