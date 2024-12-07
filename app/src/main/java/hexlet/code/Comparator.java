package hexlet.code;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.TreeSet;

public class Comparator {

    public static List<Map<String, Object>> compare(Map<String, Object> fileMap1, Map<String, Object> fileMap2) {
        var listOfMaps = new ArrayList<Map<String, Object>>();
        var setOfAllKeys = new TreeSet<>();
        setOfAllKeys.addAll(fileMap1.keySet());
        setOfAllKeys.addAll(fileMap2.keySet());
        for (var key : setOfAllKeys) {
            var maps = new HashMap<String, Object>();
            Boolean fileMap1Include = fileMap1.containsKey(key);
            Boolean fileMap2Include = fileMap2.containsKey(key);
            Boolean file12Equals = null;
            if (fileMap1.get(key) == null || fileMap2.get(key) == null) {
                file12Equals = fileMap1.get(key) == fileMap2.get(key);
            } else {
                file12Equals = fileMap1.containsKey(key) && fileMap2.containsKey(key)
                        ? (fileMap1.get(key)).equals(fileMap2.get(key)) : null;
            }
            if (fileMap1Include && fileMap2Include) {
                if (file12Equals) {
                    maps.put("  " + (String) key, fileMap1.get(key));
                } else {
                    maps.put("- " + (String) key, fileMap1.get(key));
                    maps.put("+ " + (String) key, fileMap2.get(key));
                }
            }
            if (fileMap1Include && !fileMap2Include) {
                maps.put("- " + (String) key, fileMap1.get(key));
            } else if (!fileMap1Include && fileMap2Include) {
                maps.put("+ " + (String) key, fileMap2.get(key));
            }
            listOfMaps.add(maps);
        }
        return listOfMaps;
    }
}
