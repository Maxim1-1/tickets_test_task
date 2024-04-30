package org.tickets;

import org.tickets.model.Agent;
import org.tickets.service.PriceCalculationService;
import org.tickets.service.TimeCalculationService;
import org.tickets.utils.JsonUtils;
import java.io.FileWriter;
import java.nio.file.Files;
import java.io.File;
import java.io.IOException;
import java.util.*;



public class Main {
    public static void main(String[] args) {
        try {
            String json = Files.readString(new File("src/main/resources/tickets.json").toPath());
            List<Agent> agents = JsonUtils.convertJsonToAgents(json);

            PriceCalculationService priceCalculationService = new PriceCalculationService(agents);
            TimeCalculationService timeCalculationService = new TimeCalculationService(agents);

            double differenceMedianAveragePrice = priceCalculationService.calculateDifferenceMedianAveragePrice();
            Map<String,String> minimalTimeFlight = timeCalculationService.calculationMinimumFlightTime();

            StringBuilder resultData = new StringBuilder(String.format("Разница между средней ценой и медианой = %s  \n",differenceMedianAveragePrice));

            minimalTimeFlight.forEach((key, value) -> {
                resultData.append(String.format("Минимальное время полета через компанию %s , составляет : %s \n",key,value));
            });

            writeDataInFile(resultData.toString());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void writeDataInFile (String data){
        try (FileWriter writer = new FileWriter("result.txt");) {
            writer.write(data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}