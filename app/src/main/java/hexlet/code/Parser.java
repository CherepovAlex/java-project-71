package hexlet.code;

import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

public class Parser {

    public static Map<String, Object> getDataStripes(String content, String checkInputFormat) throws Exception {
        ObjectMapper mapper = mapperChecker(checkInputFormat);
        return mapper.readValue(content, Map.class);
    }

    public static ObjectMapper mapperChecker(String mapFormat) throws Exception {
        switch (mapFormat) {
            case "json" -> { return new ObjectMapper();}
            case "yml", "yaml" -> { return new YAMLMapper();}
            default -> throw new Exception("Unsupported data format - " + mapFormat);
        }
    }
}
