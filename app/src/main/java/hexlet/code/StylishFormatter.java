package hexlet.code;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StylishFormatter {

    public static String format(List<Map<String, Object>> compareResult) {
        return "{\n " + compareResult.stream()
                .map(map -> map.entrySet().stream()
                        .sorted(Comparator.comparing(Map.Entry::getKey))
                        .map(entry -> entry.getKey() + ": " + entry.getValue())
                        .collect(Collectors.toList()).reversed())
                .map(list -> list.stream()
                        .collect(Collectors.joining("\n ")))
                .collect(Collectors.joining("\n ")) + "\n}" + "\n";
    }
}
