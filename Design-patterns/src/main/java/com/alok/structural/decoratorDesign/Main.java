package com.alok.structural.decoratorDesign;

public class Main {
    public static void main(String[] args){
        BasePizza pizza = new ExtraCheeze(new Mashroom(new Margherita()));
        System.out.println(pizza.cost());
    }
}
