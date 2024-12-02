package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AppTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(output));
    }

    @Test
    @DisplayName("'main' method works correctly")
    void testMain() throws FileNotFoundException {
        String fileTest = "/expected_results/AppTestResult.txt";
        App.main("file1.json", "file2.json");
        Scanner fileTestScanner = new Scanner(new File(fileTest));
        assertEquals(fileTestScanner, output.toString(StandardCharsets.UTF_8).trim());
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}