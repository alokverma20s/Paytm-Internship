package com.alok.problem2.structural.decorator;

public class MilkDecorator extends Coffee {
    private final Coffee coffee;

    public MilkDecorator(Coffee coffee) {
        this.coffee = coffee;
    }

    public int cost() {
        return coffee.cost() + 2;
    }
}
