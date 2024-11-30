package hexlet.code;

import java.util.*;

public class Comparator {

    public static List<Map<String, Object>> compare (Map<String, Object> fileMap1, Map<String, Object> fileMap2){
        //[
        // {                    {                       {                       {
        // "FIELD": "array"     "FIELD": "follow",      "FIELD": "timeout",     "FIELD": "verbose",
        // "STATUS": "SAME"     "STATUS": "REMOVED",    "STATUS": "UPDATED",    "STATUS": "ADDED",
        // OLD_VALUE: [         "OLD_VALUE": false      "NEW_VALUE": 28,        "NEW_VALUE": true
        // 1,                   },                      "OLD_VALUE: 50          }
        // 2,                                           },                      ]
        // ]
        // },
        var list = new ArrayList<Map<String, Object>>();
        var keys = new TreeSet<>();
        keys.addAll(fileMap1.keySet());
        keys.addAll(fileMap2.keySet());

        for (var key : keys) {
            var map = new HashMap<String, Object>();
            Boolean fileMap1In = fileMap1.containsKey(key);
            Boolean fileMap2In = fileMap2.containsKey(key);
            Boolean file12Equals = null;
            if (fileMap1In && fileMap2In) {
                file12Equals = (fileMap1.get(key)).equals(fileMap2.get(key));
            }
            if (fileMap1In && fileMap2In && file12Equals) {
                map.put("  " + (String) key, fileMap1.get(key));
                list.add(map);
            }
            if (fileMap1In && fileMap2In && !file12Equals) {
                map.put("- " + (String) key, fileMap1.get(key));
                map.put("+ " + (String) key, fileMap2.get(key));
                list.add(map);
            }
            if (fileMap1In && !fileMap2In) {
                map.put("- " + (String) key, fileMap1.get(key));
                list.add(map);
            }
            if (!fileMap1In && fileMap2In) {
                map.put("+ " + (String) key, fileMap2.get(key));
                list.add(map);
            }
        }
        return list;
    }
}
