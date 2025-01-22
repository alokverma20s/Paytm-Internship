package com.alok.behaviourDesignPattern.observerDesignPattern.observable;

import com.alok.behaviourDesignPattern.observerDesignPattern.observer.NotificationAlertObserver;

public interface StocksObservable {
    public void add(NotificationAlertObserver observer);
    public void remove(NotificationAlertObserver observer);
    public void notifySubscribers();

    public void setStockCount(int newStockAdded);
    public int getStockCount();
}
