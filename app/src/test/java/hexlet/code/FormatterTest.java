package hexlet.code;

import hexlet.code.formatters.Formatter;
import org.junit.jupiter.api.Test;
import java.util.Map;
import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FormatterTest {

    @Test
    void testStylishFormat() throws Exception {
        Map<String, KeyStatus> diff = new HashMap<>();
        diff.put("key1", new KeyStatus("unchanged", "value1"));
        diff.put("key2", new KeyStatus("changed", "oldValue", "newValue"));
        String result = Formatter.choiceFormat(diff, "stylish");
        assertTrue(result.contains("  - key2: oldValue"));
        assertTrue(result.contains("  + key2: newValue"));
    }

    @Test
    void testPlainFormat() throws Exception {
        Map<String, KeyStatus> diff = new HashMap<>();
        diff.put("key1", new KeyStatus("added", null, "value1"));
        diff.put("key2", new KeyStatus("deleted", "value2"));
        String result = Formatter.choiceFormat(diff, "plain");
        assertTrue(result.contains("Property 'key1' was added with value: 'value1'"));
        assertTrue(result.contains("Property 'key2' was removed"));
    }

    @Test
    void testJsonFormat() throws Exception {
        Map<String, KeyStatus> diff = new HashMap<>();
        diff.put("key1", new KeyStatus("unchanged", "value1"));
        String result = Formatter.choiceFormat(diff, "json");
        assertTrue(result.contains("\"key1\":{\"status\":\"unchanged\",\"value1\":\"value1\""));
    }

    @Test
    void testUnsupportedFormat() {
        Map<String, KeyStatus> diff = new HashMap<>();
        Exception exception = assertThrows(RuntimeException.class, () -> Formatter.choiceFormat(diff, "xml"));
        assertEquals("Unsupported style: xml", exception.getMessage());
    }
}
