package hexlet.code;

import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.TreeSet;

public class Comparator {

    public static List<Map<String, Object>> compare(Map<String, Object> fileMapFirst,
                                                    Map<String, Object> fileMapSecond) {
        var listOfMaps = new ArrayList<Map<String, Object>>();
        var keysOfAllMaps = new TreeSet<>();
        keysOfAllMaps.addAll(fileMapFirst.keySet());
        keysOfAllMaps.addAll(fileMapSecond.keySet());
        for (var keyForCompare : keysOfAllMaps) {
            var mapWithOrder = new HashMap<String, Object>();
            Boolean fileMapFirstInclude = fileMapFirst.containsKey(keyForCompare);
            Boolean fileMapSecondInlude = fileMapSecond.containsKey(keyForCompare);
            Boolean fileMapFistSecobdEquals = null;
            if (fileMapFirstInclude && fileMapSecondInlude) {
                fileMapFistSecobdEquals = (fileMapFirst.get(keyForCompare)).equals(fileMapSecond.get(keyForCompare));
            }
            if (fileMapFirstInclude && fileMapSecondInlude && fileMapFistSecobdEquals) {
                mapWithOrder.put("  " + (String) keyForCompare, fileMapFirst.get(keyForCompare));
                listOfMaps.add(mapWithOrder);
            }
            if (fileMapFirstInclude && fileMapSecondInlude && !fileMapFistSecobdEquals) {
                mapWithOrder.put("- " + (String) keyForCompare, fileMapFirst.get(keyForCompare));
                mapWithOrder.put("+ " + (String) keyForCompare, fileMapSecond.get(keyForCompare));
                listOfMaps.add(mapWithOrder);
            }
            if (fileMapFirstInclude && !fileMapSecondInlude) {
                mapWithOrder.put("- " + (String) keyForCompare, fileMapFirst.get(keyForCompare));
                listOfMaps.add(mapWithOrder);
            }
            if (!fileMapFirstInclude && fileMapSecondInlude) {
                mapWithOrder.put("+ " + (String) keyForCompare, fileMapSecond.get(keyForCompare));
                listOfMaps.add(mapWithOrder);
            }
        }
        return listOfMaps;
    }
}
