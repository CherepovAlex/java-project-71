package hexlet.code;

import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

public class Parser {

    public static Map<String, Object> parse(String fileContent, String format) throws IOException {
        // TODO дописать для других форматов
        ObjectMapper mapperJson = new ObjectMapper();
        ObjectMapper mapperYaml = new YAMLMapper();
        return switch (format) {
            case "json" -> mapperJson.readValue(fileContent, new TypeReference<Map<String, Object>>() {
            });
            case "yaml" -> mapperYaml.readValue(fileContent, new TypeReference<Map<String, Object>>() {
            });
            default -> throw new RuntimeException("Unsupported format");
        };
    }
}
