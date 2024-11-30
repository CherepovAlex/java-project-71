package hexlet.code;

import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Parser {

    public static Map<String, Object> parse(String fileContent, String format) throws IOException {
        // TODO дописать для других форматов
        ObjectMapper mapper = new ObjectMapper();
        return switch (format) {
            case "json" -> mapper.readValue(fileContent, new TypeReference<Map<String, Object>>() {
            });
//            case "yaml":
//            case "yml":
//                Yaml yaml = new Yaml();
//                return yaml.load(fileContent);
            default -> throw new RuntimeException("Unsupported format");
        };
    }
}
