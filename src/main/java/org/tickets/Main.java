package org.tickets;

import org.tickets.model.Agent;
import org.tickets.service.PriceCalculationService;
import org.tickets.service.TimeCalculationService;
import org.tickets.utils.JsonUtils;

import java.io.FileWriter;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.io.IOException;
import java.util.*;


public class Main {
    public static void main(String[] args) {
        try (InputStream inputStream = Main.class.getResourceAsStream("/tickets.json")){

            String json = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
            List<Agent> agents = JsonUtils.convertJsonToAgents(json);

            PriceCalculationService priceCalculationService = new PriceCalculationService(agents);
            TimeCalculationService timeCalculationService = new TimeCalculationService(agents);

            double differenceMedianAveragePrice = priceCalculationService.calculateDifferenceMedianAveragePrice();
            Map<String, String> minimalTimeFlight = timeCalculationService.calculationMinimumFlightTime();

            StringBuilder resultData = new StringBuilder(String.format("Разница между средней ценой и медианой = %s  \n", differenceMedianAveragePrice));

            minimalTimeFlight.forEach((key, value) -> {
                resultData.append(String.format("Минимальное время полета через компанию %s , составляет : %s \n", key, value));
            });

            writeDataInFile(resultData.toString());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void writeDataInFile(String data) {
        String filePath = System.getProperty("user.dir") + "/../result.txt";
        try (FileWriter writer = new FileWriter(filePath);) {
            writer.write(data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}