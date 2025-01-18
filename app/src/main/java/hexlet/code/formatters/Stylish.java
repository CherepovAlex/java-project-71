package hexlet.code.formatters;

import hexlet.code.KeyStatus;
import java.util.List;

public class Stylish {

    public static String stylishFormatter(List<KeyStatus> diff) {
        StringBuilder str = new StringBuilder();
        str.append("{\n");
        for (KeyStatus element : diff) {
            String status = element.getStatus();
            String key = element.getKey();
            var value1 = element.getValue1();
            var value2 = element.getValue2();

            switch (status) {
                case "deleted" -> str.append("  - ").append(key).append(": ").append(value1).append("\n");
                case "added" -> str.append("  + ").append(key).append(": ").append(value2).append("\n");
                case "unchanged" -> str.append("    ").append(key).append(": ").append(value1).append("\n");
                case "changed" -> str.append("  - ").append(key).append(": ").append(value1).
                        append("\n").append("  + ").append(key).append(": ").append(value2).append("\n");
                default -> {
                    return "Something went wrong for input: " + element;
                }
            }
        }
        str.append("}");
        return str.toString();
    }
}
