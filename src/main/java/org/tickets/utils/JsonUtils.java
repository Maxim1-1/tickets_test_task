package org.tickets.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.tickets.model.Agent;

import java.io.IOException;
import java.util.List;

public class JsonUtils {

    public static List<Agent> convertJsonToAgents(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(json);
        JsonNode agentsNode = rootNode.get("agents");
        List<Agent> agents = mapper.readValue(agentsNode.toString(), new TypeReference<List<Agent>>() {
        });
        return agents;
    }

}
