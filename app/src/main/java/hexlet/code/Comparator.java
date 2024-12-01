package hexlet.code;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public class Comparator {

    public static List<Map<String, Object>> compare(Map<String, Object> fileMap1, Map<String, Object> fileMap2) {
        var list = new ArrayList<Map<String, Object>>();
        var keys = new TreeSet<>();
        keys.addAll(fileMap1.keySet());
        keys.addAll(fileMap2.keySet());
        for (var key : keys) {
            if (fileMap1.containsKey(key) && fileMap2.containsKey(key)) {
                if (fileMap1.get(key) != null && fileMap1.get(key) != null
                        && ((fileMap1.get(key)).equals(fileMap2.get(key)))) {
                    list.add(Map.of("  " + (String) key, fileMap1.get(key)));
                } else {
                    list.add(Map.of("- " + (String) key, fileMap1.get(key), "+ " + (String) key, fileMap2.get(key)));
                }
            }
            if (fileMap1.containsKey(key) && !fileMap2.containsKey(key)) {
                list.add(Map.of("- " + (String) key, fileMap1.get(key)));
            }
            if (!fileMap1.containsKey(key) && fileMap2.containsKey(key)) {
                list.add(Map.of("+ " + (String) key, fileMap2.get(key)));
            }
        }
        return list;
    }
}
