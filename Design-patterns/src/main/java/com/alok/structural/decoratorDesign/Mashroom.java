package com.alok.structural.decoratorDesign;

public class Mashroom extends ToppingDecorator{
    BasePizza basePizza;
    public Mashroom(BasePizza basePizza){
        this.basePizza = basePizza;
    }
    @Override
    public int cost() {
        return this.basePizza.cost() + 20;
    }
}
