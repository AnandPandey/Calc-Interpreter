import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        if (args.length == 1) {
            runFile(args[0]);
        } else {
            runRepl();
        }
    }

    private static void runFile(String path) {
        try {
            byte[] bytes = Files.readAllBytes(Paths.get(path));
            String sourceCode = new String(bytes, StandardCharsets.UTF_8);
            new Interpreter().run(sourceCode);
        } catch (IOException e) {
            System.err.println(Colors.RED + "Error: Could not read file '" + path + "'" + Colors.RESET);
            System.exit(1);
        } catch (CalcException e) {
            System.err.println(Colors.RED + "Error: " + e.getMessage() + Colors.RESET);
            System.exit(1);
        } catch (Exception e) {
            System.err.println(Colors.RED + "Unexpected error: " + e.getMessage() + Colors.RESET);
            System.exit(1);
        }
    }

    private static void runRepl() {
        Scanner scanner = new Scanner(System.in);
        Interpreter interpreter = new Interpreter(); // shared state across lines

        System.out.println(Colors.CYAN + "Calc Interpreter REPL" + Colors.RESET);
        System.out.println(Colors.CYAN + "Type 'exit' to quit, 'load <file>' to run a file" + Colors.RESET);
        System.out.println(Colors.CYAN + "─────────────────────────────────────────────" + Colors.RESET);

        while (true) {
            System.out.print(Colors.YELLOW + ">>> " + Colors.RESET);
            String line = scanner.nextLine().trim();

            if (line.equals("exit")) break;

            if (line.isEmpty()) continue;

            // Load file command
            if (line.startsWith("load ")) {
                String path = line.substring(5).trim();
                try {
                    byte[] bytes = Files.readAllBytes(Paths.get(path));
                    String sourceCode = new String(bytes, StandardCharsets.UTF_8);
                    interpreter.run(sourceCode);
                } catch (IOException e) {
                    System.err.println(Colors.RED + "Error: Could not read file '" + path + "'" + Colors.RESET);
                } catch (CalcException e) {
                    System.err.println(Colors.RED + "Error: " + e.getMessage() + Colors.RESET);
                } catch (Exception e) {
                    System.err.println(Colors.RED + "Unexpected error: " + e.getMessage() + Colors.RESET);
                }
                continue;
            }

            // Run typed input
            try {
                interpreter.run(line);
            } catch (CalcException e) {
                System.err.println(Colors.RED + "Error: " + e.getMessage() + Colors.RESET);
            } catch (Exception e) {
                System.err.println(Colors.RED + "Unexpected error: " + e.getMessage() + Colors.RESET);
            }
        }

        System.out.println(Colors.CYAN + "Goodbye!" + Colors.RESET);
        scanner.close();
    }
}