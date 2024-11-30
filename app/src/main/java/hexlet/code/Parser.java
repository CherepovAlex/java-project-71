package hexlet.code;

import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;

public class Parser {

    public static Map<String, Object> parse (String fileContent, String format) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        switch (format) {
            case "json":
                return mapper.readValue(fileContent, new TypeReference<Map<String, Object>>() {});
            default:
                return Map.of();
        }
    }
}
