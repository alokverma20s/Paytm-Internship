package com.alok.service;

import com.alok.model.Stock;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class StockService {

    private final Random random = new Random();

    public Stock generateRandomStock() {
        String[] symbols = {"AAPL", "GOOGL", "AMZN", "MSFT", "TSLA"};
        String symbol = symbols[random.nextInt(symbols.length)];
        double price = 50 + (1000 - 50) * random.nextDouble(); // Random price between 50 and 1000
        int volume = random.nextInt(1000) + 100; // Random volume between 100 and 1000
        return new Stock(symbol, price, volume);
    }
}
