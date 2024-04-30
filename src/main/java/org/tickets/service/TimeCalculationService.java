package org.tickets.service;

import org.tickets.model.Agent;
import org.tickets.model.Flight;

import java.util.*;
import java.util.concurrent.TimeUnit;


public class TimeCalculationService {
    private final List<Agent> agents;

    public TimeCalculationService(List<Agent> agents) {
        this.agents = agents;
    }

    public Map<String, String> calculationMinimumFlightTime() {
        Map<String, String> result = new HashMap<>();
        for (Agent agent : agents) {
            Date departureDate = agent.getFlights().stream()
                    .map(Flight::getData)
                    .min(Date::compareTo)
                    .orElse(null);

            Date arrivalDate = agent.getFlights().stream()
                    .map(Flight::getData)
                    .max(Date::compareTo)
                    .orElse(null);

            if (departureDate != null && arrivalDate != null) {
                Optional<Flight> durationFlight = agent.getFlights().stream()
                        .filter(flight -> flight.getData().equals(arrivalDate))
                        .findFirst();

                if (durationFlight.isPresent()) {
                    long flightDurationInMinutes = TimeUnit.MILLISECONDS.toMinutes(arrivalDate.getTime() - departureDate.getTime()) + durationFlight.get().getDuration();
                    long days = TimeUnit.MINUTES.toDays(flightDurationInMinutes);
                    long hours = TimeUnit.MINUTES.toHours(flightDurationInMinutes) % 24;
                    long minutes = flightDurationInMinutes % 60;

                    result.put(agent.getName(), String.format("%d д. %d ч. %d мин.", days, hours, minutes));
                }
            } else return null;
        }
        return result;
    }
}
