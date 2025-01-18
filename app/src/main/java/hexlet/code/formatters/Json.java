package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.KeyStatus;

public class Json {

    public static String jsonFormatter(List<KeyStatus> diff) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Map<String, Object>> resultMap = new LinkedHashMap<>();

        for (KeyStatus keyStatus : diff) {
            Map<String, Object> statusMap = new LinkedHashMap<>();
            statusMap.put("status", keyStatus.getStatus());
            statusMap.put("value1", keyStatus.getValue1());
            statusMap.put("value2", keyStatus.getValue2());
            resultMap.put(keyStatus.getKey(), statusMap);
        }

        return mapper.writeValueAsString(resultMap);
    }
}
