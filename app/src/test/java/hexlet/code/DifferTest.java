package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.skyscreamer.jsonassert.JSONAssert;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static org.assertj.core.api.Assertions.assertThat;

public final class DifferTest {

    private static String resultJson;
    private static String resultPlain;
    private static String resultStylish;

    @BeforeAll
    public static void beforeAll() throws Exception {
        resultJson = readExpectedResults("result_json.json");
        resultPlain = readExpectedResults("result_plain.txt");
        resultStylish = readExpectedResults("result_stylish.txt");
    }

    private static String readExpectedResults(String fileName) throws Exception {
        return Files.readString(Path.of("src/test/resources/expected_results/" + fileName)).trim();
    }

    private static Path getExpectedResultsPath(String fileName) {
        return Paths.get("src", "test", "resources", "expected_results", fileName)
                .toAbsolutePath().normalize();
    }

    @ParameterizedTest
    @ValueSource(strings = {"json", "yml"})
    public void generateTest(String format) throws Exception {
        String filePath1 = getExpectedResultsPath("file1." + format).toString();
        String filePath2 = getExpectedResultsPath("file2." + format).toString();

        assertThat(Differ.generate(filePath1, filePath2))
                .isEqualTo(resultStylish);

        assertThat(Differ.generate(filePath1, filePath2, "stylish"))
                .isEqualTo(resultStylish);

        assertThat(Differ.generate(filePath1, filePath2, "plain"))
                .isEqualTo(resultPlain);

        String actualJson = Differ.generate(filePath1, filePath2, "json");
        JSONAssert.assertEquals(resultJson, actualJson, false);
    }
}