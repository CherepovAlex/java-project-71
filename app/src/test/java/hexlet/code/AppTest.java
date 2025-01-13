package hexlet.code;

import org.junit.jupiter.api.Test;
import picocli.CommandLine;
import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    @Test
    void testAppWithValidInput() {
        App app = new App();
        CommandLine cmd = new CommandLine(app);
        int exitCode = cmd.execute("src/test/resources/expected_results/file1.json",
                "src/test/resources/expected_results/file2.json");
        assertEquals(0, exitCode);
    }

    @Test
    void testAppWithInvalidFile() {
        App app = new App();
        CommandLine cmd = new CommandLine(app);
        int exitCode = cmd.execute("nonexistent.json",
                "src/test/resources/expected_results/file2.json");
        assertEquals(1, exitCode);
    }

    @Test
    void testAppWithFormatOption() {
        App app = new App();
        CommandLine cmd = new CommandLine(app);
        int exitCode = cmd.execute("--f", "plain", "src/test/resources/expected_results/file1.json",
                "src/test/resources/expected_results/file2.json");
        assertEquals(0, exitCode);
    }
}