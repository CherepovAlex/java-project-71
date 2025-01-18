package hexlet.code;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.Objects;

public class FileComparator {

    public static List<KeyStatus> compare(Map<String, Object> firstData, Map<String, Object> secondData) {
        List<KeyStatus> result = new ArrayList<>();
        Set<String> keySet = new TreeSet<>(firstData.keySet());
        keySet.addAll(secondData.keySet());

        for (String key : keySet) {
            Object value1 = firstData.get(key);
            Object value2 = secondData.get(key);
            if (!firstData.containsKey(key)) {
                result.add(new KeyStatus(key, "added", null, value2));
            } else if (!secondData.containsKey(key)) {
                result.add(new KeyStatus(key, "deleted", value1));
            } else if (Objects.equals(value1, value2)) {
                result.add(new KeyStatus(key, "unchanged", value1));
            } else if (!Objects.equals(value1, value2)) {
                result.add(new KeyStatus(key, "changed", value1, value2));
            }
        }
        return result;
    }
}
