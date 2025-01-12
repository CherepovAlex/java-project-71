package hexlet.code;

import hexlet.code.formatters.Formatter;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class Differ {
    public static String generate(String filePath1, String filePath2) throws Exception {
        return generate(filePath1, filePath2, "stylish");
    }

    public static String generate(String filepath1, String filepath2, String formatName) throws Exception {
        Path fullFilePath1 = getFullPath(filepath1);
        Path fullFilePath2 = getFullPath(filepath2);

        String fileFormat1 = getDataFormat(String.valueOf(fullFilePath1));
        String fileFormat2 = getDataFormat(String.valueOf(fullFilePath2));

        String contentFile1 = Files.readString(fullFilePath1);
        String contentFile2 = Files.readString(fullFilePath2);

        Map<String, Object> fileMap1 = Parser.getDataStripes(contentFile1, fileFormat1);
        Map<String, Object> fileMap2 = Parser.getDataStripes(contentFile2, fileFormat2);

        Map<String, KeyStatus> compareResult = Comparator.compare(fileMap1, fileMap2);

        if (compareResult.isEmpty()) {
            return "The files are empty.";
        }

        String result = Formatter.choiceFormat(compareResult, formatName);

        return result;
    }

    //читайем файл и переводим его в большую строку; не нужен отдельный класс
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

    public static Path getFullPath(String path) {
        return Paths.get(path).toAbsolutePath().normalize();
    }

    private static String getDataFormat(String filePath) {
        int index = filePath.lastIndexOf('.');
        return index > 0 ? filePath.substring(index + 1) : "";
    }
}
