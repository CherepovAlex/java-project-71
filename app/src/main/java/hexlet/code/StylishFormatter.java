package hexlet.code;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StylishFormatter {

    public static String format(List<Map<String, Object>> compareResult) {
        String result = compareResult.stream()
                .map(map -> map.entrySet().stream()
                        .map(entry -> entry.getKey() + ": " + entry.getValue())
                        .collect(Collectors.toList()))
                        .map(list -> list.stream()
                        .collect(Collectors.joining("\n ")))
                        .collect(Collectors.joining("\n "));
        return "{\n " + result + "\n}" + "\n";
    }
}
