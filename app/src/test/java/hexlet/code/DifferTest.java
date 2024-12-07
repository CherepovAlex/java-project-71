package hexlet.code;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static hexlet.code.App.getAbsolutePath;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();
    private final String expectedResultStylish1 = "src/test/resources/expected_results/DiffTestResult1.txt";
    private final String expectedResultStylish2 = "src/test/resources/expected_results/DiffTestResult2.txt";
    StringBuilder sb1 = new StringBuilder();
    StringBuilder sb2 = new StringBuilder();

    @BeforeEach
    public void setUp() throws FileNotFoundException {
        System.setOut(new PrintStream(output));
        File file1 = new File(String.valueOf(getAbsolutePath(expectedResultStylish1)));
        File file2 = new File(String.valueOf(getAbsolutePath(expectedResultStylish2)));
        try (BufferedReader br = new BufferedReader(new FileReader(file1))) {
            String line;
            while ((line = br.readLine()) != null) {
                sb1.append(line).append("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file2))) {
            String line;
            while ((line = br.readLine()) != null) {
                sb2.append(line).append("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"json", "yaml"})
    public void testGenerateWithJsonOutput1(String inputFormat) throws Exception {
        var file1 = getAbsolutePath("file1." + inputFormat).toString();
        var file2 = getAbsolutePath("file2." + inputFormat).toString();

        String actualResult = Differ.generate(file1, file2, "stylish");
        assertEquals(actualResult, sb1.toString());
    }

    @ParameterizedTest
    @ValueSource(strings = {"json", "yaml"})
    public void testGenerateWithJsonOutput2(String inputFormat) throws Exception {
        var file1 = getAbsolutePath("file3." + inputFormat).toString();
        var file2 = getAbsolutePath("file4." + inputFormat).toString();

        String actualResult = Differ.generate(file1, file2, "stylish");
        assertEquals(actualResult, sb2.toString());
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}
