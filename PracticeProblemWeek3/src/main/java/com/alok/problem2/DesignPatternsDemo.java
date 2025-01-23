package com.alok.problem2;

// Design Patterns Demonstration in Java

import com.alok.problem2.behavioral.chainOfResponsibility.*;

public class DesignPatternsDemo {
    public static void main(String[] args) {
        // Factory
//        Shape circle = ShapeFactory.getShape("Circle");
//        System.out.println(circle.draw());

        // Singleton
//        Singleton singleton1 = Singleton.getInstance();
//        Singleton singleton2 = Singleton.getInstance();
//        System.out.println(singleton1 == singleton2);
//
//        // Builder
//        CarBuilder builder = new CarBuilder();
//        Car car = builder.addWheels().addEngine().addBody().build();
//        System.out.println(car.showParts());
//
//        // Adapter
//        EuropeanPlug europeanPlug = new EuropeanPlug();
//        USPlug adapter = new USPlugAdapter(europeanPlug);
//        System.out.println(adapter.connect());
//
//        // Decorator
//        Coffee coffee = new Coffee();
//        Coffee coffeeWithMilk = new MilkDecorator(coffee);
//        Coffee coffeeWithMilkAndSugar = new SugarDecorator(coffeeWithMilk);
//        System.out.println(coffeeWithMilkAndSugar.cost());
//
//        // Observer
//        Subject subject = new Subject();
//        Observer observer = new ConcreteObserver();
//        subject.attach(observer);
//        subject.notifyObservers();
//
//        // State
//        Context context = new Context(new StateA());
//        System.out.println(context.request());
//        context.setState(new StateB());
//        System.out.println(context.request());
//
//        // Strategy
//        ContextStrategy contextStrategy = new ContextStrategy(new StrategyA());
//        System.out.println(contextStrategy.executeStrategy(Arrays.asList(3, 1, 2)));
//
//        // Command
//        Light light = new Light();
//        Command lightOn = new LightOnCommand(light);
//        Command lightOff = new LightOffCommand(light);
//        System.out.println(lightOn.execute());
//        System.out.println(lightOff.execute());
//
//        // Chain of Responsibility
        LogProcessor logObject = new InfoLogProcessor(new DebugLogProcessor(new ErrorLogProcessor(null)));

        logObject.log(LogProcessor.ERROR, "Exception Happens");
        logObject.log(LogProcessor.DEBUG, "Need to debug this");
        logObject.log(LogProcessor.INFO, "Just for info");
    }
}
