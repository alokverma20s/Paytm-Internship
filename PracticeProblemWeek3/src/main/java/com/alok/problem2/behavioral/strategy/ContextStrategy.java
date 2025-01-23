package com.alok.problem2.behavioral.strategy;

import java.util.List;

public class ContextStrategy {
    private Strategy strategy;

    public ContextStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public List<Integer> executeStrategy(List<Integer> data) {
        return strategy.execute(data);
    }
}
