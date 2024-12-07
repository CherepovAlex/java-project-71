package hexlet.code;

import static hexlet.code.App.getAbsolutePath;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.ValueSource;

class AppTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();

    private final String expectedResultJson = "/expected_results/AppTestResult.txt";

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(output));
    }

    @Test
    @DisplayName("'main' method works correctly")
    void testMain() throws FileNotFoundException {
        var file1 = getAbsolutePath("file1." + "json").toString();
        var file2 = getAbsolutePath("file1." + "json").toString();
        App.main(file1, file2);
        assertEquals(expectedResultJson, output.toString(StandardCharsets.UTF_8).trim());
    }

    @Test
    @DisplayName("'main' method works correctly")
    void testMain2() throws FileNotFoundException {
        var file1 = getAbsolutePath("file1.yaml").toString();
        var file2 = getAbsolutePath("file1.yaml").toString();
        App.main(file1, file2);
        assertEquals(expectedResultJson, output.toString(StandardCharsets.UTF_8).trim());
    }


    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}
