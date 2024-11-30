package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.*;
import java.util.Map;

public class Differ {

    public static String generate(String filepath1, String filepath2, String format) throws IOException {
        String content1 = readFile(filepath1);
        String content2 = readFile(filepath2);

        String fileFormat1 = getFileType(filepath1);
        String fileFormat2 = getFileType(filepath2);

        Map<String, Object> file1 = Parser.parse(content1, fileFormat1);
        Map<String, Object> file2 = Parser.parse(content2, fileFormat2);



        return "";
    }

    private static String readFile(String filePath) {
        File file = new File(filePath);
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return sb.toString();
    }

    private static String getFileType(String filePath) {
        String[] arr = "\\".split(filePath);
        return arr[arr.length - 1];
    }
}
