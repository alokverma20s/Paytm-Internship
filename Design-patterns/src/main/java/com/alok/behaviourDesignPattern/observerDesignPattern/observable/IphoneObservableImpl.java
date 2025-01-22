package com.alok.behaviourDesignPattern.observerDesignPattern.observable;

import com.alok.behaviourDesignPattern.observerDesignPattern.observer.NotificationAlertObserver;

import java.util.ArrayList;
import java.util.List;

public class IphoneObservableImpl implements StocksObservable{

    public List<NotificationAlertObserver> notificationAlertObservers = new ArrayList<>();
    public int stockCount = 0;

    @Override
    public void add(NotificationAlertObserver observer) {
        notificationAlertObservers.add(observer);
    }

    @Override
    public void remove(NotificationAlertObserver observer) {
        notificationAlertObservers.remove(observer);
    }

    @Override
    public void notifySubscribers() {
        for(NotificationAlertObserver notificationAlertObserver: notificationAlertObservers){
            notificationAlertObserver.update();
        }
    }

    @Override
    public void setStockCount(int newStockAdded) {
        if(stockCount == 0){
            notifySubscribers();
        }
        stockCount = stockCount + newStockAdded;
    }

    @Override
    public int getStockCount() {
        return stockCount;
    }
}
