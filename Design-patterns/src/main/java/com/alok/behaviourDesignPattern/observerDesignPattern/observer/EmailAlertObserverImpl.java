package com.alok.behaviourDesignPattern.observerDesignPattern.observer;

import com.alok.behaviourDesignPattern.observerDesignPattern.observable.StocksObservable;

public class EmailAlertObserverImpl implements NotificationAlertObserver{
    private final String emailId;
    private final StocksObservable stocksObservable;


    public EmailAlertObserverImpl(String emailId, StocksObservable stocksObservable){
        this.emailId = emailId;
        this.stocksObservable = stocksObservable;
    }

    @Override
    public void update() {
        sendMail(emailId, "Product is in stock hurry up!");
    }

    private void sendMail(String emailId, String message) {
        System.out.println("Mail send to : " + emailId);
        System.out.println("Message is : " + message);
    }
}
