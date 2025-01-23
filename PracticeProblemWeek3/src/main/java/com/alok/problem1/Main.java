package com.alok.problem1;

interface AnimalBehavior {
    void eat();
    void sleep();
}

abstract class Animal implements AnimalBehavior {
    String name;
    public Animal(String name) {
        this.name = name;
    }

    abstract void makeSound();
    @Override
    public void sleep() {
        System.out.println(name + " is sleeping.");
    }
}

class Dog extends Animal {

    public Dog(String name) {
        super(name);
    }

    @Override
    void makeSound() {
        System.out.println(name + " says: Woof Woof!");
    }

    @Override
    public void eat() {
        System.out.println(name + " is eating dog food.");
    }
}

public class Main {
    public static void main(String[] args) {
        Dog myDog = new Dog("Buddy");
        myDog.makeSound();
        myDog.eat();
        myDog.sleep();
    }
}
