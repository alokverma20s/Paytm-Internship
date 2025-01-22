package com.alok.structural.decoratorDesign;

public class ExtraCheeze extends ToppingDecorator{
    BasePizza basePizza;
    public ExtraCheeze(BasePizza basePizza){
        this.basePizza = basePizza;
    }
    @Override
    public int cost() {
        return this.basePizza.cost() + 10;
    }
}
