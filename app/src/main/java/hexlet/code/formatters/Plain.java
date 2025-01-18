package hexlet.code.formatters;

import hexlet.code.KeyStatus;
import java.lang.reflect.Array;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Plain {

    public static String plainFormatter(List<KeyStatus> diff) {
        StringBuilder str = new StringBuilder();
        for (KeyStatus element : diff) {

            String status = element.getStatus();
            String key = element.getKey();
            var value1 = element.getValue1();
            var value2 = element.getValue2();

            var valueResult1 = prepareValues(value1);
            var valueResult2 = prepareValues(value2);

            switch (status) {
                case "deleted" -> str.append("Property '").append(key).append("' was removed").append("\n");
                case "added" -> str.append("Property '").append(key).append("' was added with value: ")
                        .append(valueResult2).append("\n");
                case "changed" -> str.append("Property '").append(key).append("' was updated. ")
                        .append("From ").append(valueResult1).append(" to ").append(valueResult2).append("\n");
                case "unchanged" -> { }
                default -> {
                    return "Something went wrong for input: " + element;
                }
            }
        }
        return str.toString().trim();
    }

    private static String prepareValues(Object value) {
        if (value instanceof String) {
            return "'" + value + "'";
        } else if (value instanceof Map || value instanceof Array || value instanceof List) {
            return "[complex value]";
        }
        return Objects.toString(value);
    }
}
