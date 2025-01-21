package com.alok.model;

public class Stock {
    private String symbol;
    private double price;
    private int volume;

    @Override
    public String toString() {
        return "{" +
                "\"symbol\": \"" + symbol + '\"' +
                ", \"price\": \"" + price +
                "\", \"volume\": \"" + volume +
                "\"}";
    }

    public Stock(String symbol, double price, int volume) {
        this.symbol = symbol;
        this.price = price;
        this.volume = volume;
    }

    // Getters and setters
    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }
}
