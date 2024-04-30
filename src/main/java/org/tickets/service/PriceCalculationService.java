package org.tickets.service;

import org.tickets.model.Agent;
import java.util.List;
import java.util.stream.Collectors;

public class PriceCalculationService {
    private final List<Agent> agents;
    public PriceCalculationService(List<Agent> agents) {
        this.agents = agents;
    }

    public double calculateDifferenceMedianAveragePrice () {
        var s = calculateMedian();
        var ss = calculateAveragePrice();
        return calculateAveragePrice()-calculateMedian() ;

    }

    private double calculateMedian() {
        List<Integer> prices = agents.stream()
                .map(Agent::getPrice)
                .sorted()
                .collect(Collectors.toList());

        double medianPrice;
        int size = prices.size();
        if (size % 2 == 0) {
            medianPrice = (prices.get(size/2 - 1) + prices.get(size/2)) / 2.0;
        } else {
            medianPrice = prices.get(size/2);
        }
        return medianPrice;

    }

    private double calculateAveragePrice() {
        return agents.stream()
                .mapToInt(Agent::getPrice)
                .average()
                .orElse(0);
    }


}
