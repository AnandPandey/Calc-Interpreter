import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

// Entry point — reads .calc file and runs it through the interpreter
// Usage: java Main samples/program1.calc
public class Main {

    public static void main(String[] args) {

        if (args.length != 1) {
            System.err.println("Usage: java Main <source-file.calc>");
            System.exit(1);
        }

        String filePath = args[0];
        String sourceCode;

        try {
            byte[] bytes = Files.readAllBytes(Paths.get(filePath));
            sourceCode = new String(bytes, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.err.println("Error: Could not read file '" + filePath + "'");
            System.exit(1);
            return; // unreachable but required for compiler flow analysis
        }

        try {
            new Interpreter().run(sourceCode);
        } catch (CalcException e) {
            System.err.println("Error: " + e.getMessage());
            System.exit(1);
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
            System.exit(1);
        }
    }
}