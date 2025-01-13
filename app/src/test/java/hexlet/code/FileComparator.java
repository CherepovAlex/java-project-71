package hexlet.code;

import org.junit.jupiter.api.Test;
import java.util.Map;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FileComparatorTest {

    @Test
    void testCompareWithAddedKey() {
        Map<String, Object> map1 = new HashMap<>();
        Map<String, Object> map2 = new HashMap<>();
        map2.put("key1", "value1");

        Map<String, KeyStatus> result = FileComparator.compare(map1, map2);
        assertEquals("added", result.get("key1").getStatus());
    }

    @Test
    void testCompareWithDeletedKey() {
        Map<String, Object> map1 = new HashMap<>();
        Map<String, Object> map2 = new HashMap<>();
        map1.put("key1", "value1");

        Map<String, KeyStatus> result = FileComparator.compare(map1, map2);
        assertEquals("deleted", result.get("key1").getStatus());
    }

    @Test
    void testCompareWithChangedValue() {
        Map<String, Object> map1 = new HashMap<>();
        Map<String, Object> map2 = new HashMap<>();
        map1.put("key1", "value1");
        map2.put("key1", "value2");

        Map<String, KeyStatus> result = FileComparator.compare(map1, map2);
        assertEquals("changed", result.get("key1").getStatus());
    }

    @Test
    void testCompareWithUnchangedValue() {
        Map<String, Object> map1 = new HashMap<>();
        Map<String, Object> map2 = new HashMap<>();
        map1.put("key1", "value1");
        map2.put("key1", "value1");

        Map<String, KeyStatus> result = FileComparator.compare(map1, map2);
        assertEquals("unchanged", result.get("key1").getStatus());
    }
}
