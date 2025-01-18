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
        Path fullFilePath1 = getFullPath(filepath1);
        Path fullFilePath2 = getFullPath(filepath2);

        String contentFile1 = readFileContent(fullFilePath1);
        String contentFile2 = readFileContent(fullFilePath2);

        Map<String, Object> fileMap1 = parseContent(contentFile1, getDataFormat(fullFilePath1.toString()));
        Map<String, Object> fileMap2 = parseContent(contentFile2, getDataFormat(fullFilePath2.toString()));

        List<KeyStatus> diff = FileComparator.compare(fileMap1, fileMap2);

        return Formatter.choiceFormat(diff, formatName);
    }

    private static String readFileContent(Path filePath) throws Exception {
        return Files.readString(filePath);
    }

    private static Map<String, Object> parseContent(String content, String format) throws Exception {
        return Parser.parseContent(content, format);
    }

    private static String getDataFormat(String filePath) {
        int index = filePath.lastIndexOf('.');
        return index > 0 ? filePath.substring(index + 1) : "";
    }

    public static Path getFullPath(String path) {
        return Paths.get(path).toAbsolutePath().normalize();
    }
}
