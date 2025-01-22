package com.alok.behaviourDesignPattern.observerDesignPattern;

import com.alok.behaviourDesignPattern.observerDesignPattern.observable.IphoneObservableImpl;
import com.alok.behaviourDesignPattern.observerDesignPattern.observable.StocksObservable;
import com.alok.behaviourDesignPattern.observerDesignPattern.observer.EmailAlertObserverImpl;
import com.alok.behaviourDesignPattern.observerDesignPattern.observer.MobileAlertObserverImpl;
import com.alok.behaviourDesignPattern.observerDesignPattern.observer.NotificationAlertObserver;

public class Store {
    public static void main(String[] args) {
        StocksObservable stocksObservable = new IphoneObservableImpl();

        NotificationAlertObserver notificationAlertObserver1 = new EmailAlertObserverImpl("abc@gmail.com", stocksObservable);
        NotificationAlertObserver notificationAlertObserver2 = new EmailAlertObserverImpl("xyz@gmail.com", stocksObservable);
        NotificationAlertObserver notificationAlertObserver3 = new MobileAlertObserverImpl("alokverma20s", stocksObservable);

        stocksObservable.add(notificationAlertObserver1);
        stocksObservable.add(notificationAlertObserver2);
        stocksObservable.add(notificationAlertObserver3);

        stocksObservable.setStockCount(10);
    }

}
