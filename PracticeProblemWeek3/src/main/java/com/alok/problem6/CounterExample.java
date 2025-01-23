package com.alok.problem6;

public class CounterExample {
    private static int counter = 0;

    public static void increment() {
        counter++;
    }

    public static int getCounter() {
        return counter;
    }

    public static void main(String[] args) {
        System.out.println("Initial counter value: " + CounterExample.getCounter());

        CounterExample.increment();
        System.out.println("Counter after 1st increment: " + CounterExample.getCounter());

        CounterExample.increment();
        CounterExample.increment();
        System.out.println("Counter after 3 increments: " + CounterExample.getCounter());
    }
}
