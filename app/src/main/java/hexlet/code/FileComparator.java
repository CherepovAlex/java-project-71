package hexlet.code;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class FileComparator {

    public static Map<String, KeyStatus> compare(Map<String, Object> firstData, Map<String, Object> secondData) {
        Map<String, KeyStatus> result = new LinkedHashMap<>();
        Set<String> keySet = new TreeSet<>(firstData.keySet());
        keySet.addAll(secondData.keySet());

        for (String key : keySet) {
            Object value1 = firstData.get(key);
            Object value2 = secondData.get(key);
            if (!firstData.containsKey(key)) {
                result.put(key, new KeyStatus("added", null, value2));
            } else if (!secondData.containsKey(key)) {
                result.put(key, new KeyStatus("deleted", value1));
            } else if (Objects.equals(value1, value2)) {
                result.put(key, new KeyStatus("unchanged", value1));
            } else if (!Objects.equals(value1, value2)) {
                result.put(key, new KeyStatus("changed", value1, value2));
            }
        }
        return result;
    }
}
