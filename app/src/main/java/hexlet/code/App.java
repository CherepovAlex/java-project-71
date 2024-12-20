package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.Callable;

@Command(name = "gendiff ", mixinStandardHelpOptions = true, description =
        "Compares two configuration files and shows a difference.")

public class App implements Callable<Integer> {
    @CommandLine.Parameters(index = "0", paramLabel = "filepath1", description = "path to first file")
    private String filepath1;
    @CommandLine.Parameters(index = "1", paramLabel = "filepath2", description = "path to second file")
    private String filepath2;

    @CommandLine.Option(
            names = {"--f", "--format"},
            paramLabel = "format",
            defaultValue = "stylish",
            description = "output format [default: stylish]"
    )
    private String format;

    public static Path getAbsolutePath(String filepath) {
        return Paths.get(filepath).toAbsolutePath();
    }

    @Override
    public Integer call() throws Exception {
        System.out.println(Differ.generate(getAbsolutePath(filepath1).toString(),
                getAbsolutePath(filepath2).toString(), format));
        return 0;
    }

    public static void main(String... args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
