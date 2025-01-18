package hexlet.code.formatters;

import hexlet.code.KeyStatus;
import java.util.List;

public class Formatter {

    public static String choiceFormat(List<KeyStatus> diff, String style) throws Exception {
        String result;
        switch (style) {
            case "stylish" -> {
                result = Stylish.stylishFormatter(diff);
            }
            case "plain" -> {
                result = Plain.plainFormatter(diff);
            }
            case "json" -> {
                result = Json.jsonFormatter(diff);
            }
            default -> throw new RuntimeException("Unsupported style: " + style);
        }
        return result;
    }
}
