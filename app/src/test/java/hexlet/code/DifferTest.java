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
    private final String expectedResultStylish = "src/test/resources/expected_results/DiffTestResult.txt";
    StringBuilder sb = new StringBuilder();

    @BeforeEach
    public void setUp() throws FileNotFoundException {
        System.setOut(new PrintStream(output));
        File file = new File(String.valueOf(getAbsolutePath(expectedResultStylish)));
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"json", "yaml"})
    public void testGenerateWithJsonOutput(String inputFormat) throws Exception {
        var file1 = getAbsolutePath("file1." + inputFormat).toString();
        var file2 = getAbsolutePath("file2." + inputFormat).toString();

        String actualResult = Differ.generate(file1, file2, "stylish");
        assertEquals(actualResult, sb.toString());
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}
