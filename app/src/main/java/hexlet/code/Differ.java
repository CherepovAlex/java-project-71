package hexlet.code;

import hexlet.code.formatters.Formatter;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.List;

public class Differ {
    public static String  generate(String filePath1, String filePath2) throws Exception {
        return generate(filePath1, filePath2, "stylish");
    }

    public static String generate(String filepath1, String filepath2, String formatName) throws Exception {
        String contentFile1 = Files.readString(getFullPath(filepath1));
        String contentFile2 = Files.readString(getFullPath(filepath2));

        String format1 = getDataFormat(filepath1);
        String format2 = getDataFormat(filepath2);

        Map<String, Object> fileMap1 = Parser.parseContent(contentFile1, format1);
        Map<String, Object> fileMap2 = Parser.parseContent(contentFile2, format2);

        List<KeyStatus> diff = FileComparator.compare(fileMap1, fileMap2);

        return Formatter.choiceFormat(diff, formatName);
    }

    private static Path getFullPath(String path) {
        return Paths.get(path).toAbsolutePath().normalize();
    }

    private static String getDataFormat(String filePath) {
        return filePath.substring(filePath.lastIndexOf('.') + 1);
    }
}
