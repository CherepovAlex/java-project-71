package hexlet.code;

import java.io.*;
import java.util.List;
import java.util.Map;

public class Differ {

    public static String generate(String filepath1, String filepath2, String format) throws IOException {
        String content1 = readFile(filepath1);
        String content2 = readFile(filepath2);

        String fileFormat1 = getFileType(filepath1);
        String fileFormat2 = getFileType(filepath2);

        Map<String, Object> fileMap1 = Parser.parse(content1, fileFormat1);
        Map<String, Object> fileMap2 = Parser.parse(content2, fileFormat2);

        List<Map<String, Object>> compareResult = Comparator.compare(fileMap1, fileMap2);

        return format(compareResult, format);
    }

    private static String readFile(String filePath) {
        File file = new File(filePath);
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return sb.toString();
    }

    private static String getFileType(String filePath) {
        String[] arr = filePath.split("\\.");
        return arr[arr.length - 1];
    }

    public static String format(List<Map<String, Object>> compareResult, String format) {
        //TODO дописать для других форматов
        return switch (format) {
            case "stylish" -> StylishFormatter.format(compareResult);
            default -> throw new RuntimeException("Unsupported format");
        };
    }
}
