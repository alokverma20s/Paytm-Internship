package com.alok.behaviourDesignPattern.observerDesignPattern.observer;

import com.alok.behaviourDesignPattern.observerDesignPattern.observable.StocksObservable;

public class MobileAlertObserverImpl implements NotificationAlertObserver{
    private String username;
    private StocksObservable stocksObservable;


    public MobileAlertObserverImpl(String username, StocksObservable stocksObservable){
        this.stocksObservable = stocksObservable;
        this.username = username;
    }

    @Override
    public void update() {
        sendMail(username, "Product is in stock hurry up!");
    }

    private void sendMail(String username, String message) {
        System.out.println("Alert send to : " + username);
        System.out.println("Message is : " + message);
    }
}
