package com.alok.problem2.behavioral.strategy;

import java.util.Comparator;
import java.util.List;

public class StrategyB implements Strategy {
    public List<Integer> execute(List<Integer> data) {
        data.sort(Comparator.reverseOrder());
        return data;
    }
}
