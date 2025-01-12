package hexlet.code;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class Comparator {

    public static Map<String, KeyStatus> compare(Map<String, Object> firstData, Map<String, Object> secondData) {
        Map<String, KeyStatus> result = new LinkedHashMap<>();
        Set<String> keySet = new TreeSet<>(firstData.keySet());
        keySet.addAll(secondData.keySet());

        for (String key : keySet) {
            if (!firstData.containsKey(key)) {
                result.put(key, new KeyStatus("added", null, secondData.get(key)));
            } else if (!secondData.containsKey(key)) {
                result.put(key, new KeyStatus("deleted", firstData.get(key)));
            } else if (Objects.equals(firstData.get(key), secondData.get(key))) {
                result.put(key, new KeyStatus("unchanged", firstData.get(key)));
            } else if (!Objects.equals(firstData.get(key), secondData.get(key))) {
                result.put(key, new KeyStatus("changed", firstData.get(key), secondData.get(key)));
            }
        }
        return result;
    }
}
