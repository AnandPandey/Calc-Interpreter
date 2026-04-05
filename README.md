# Calc Interpreter

A Java-based interpreter for **Calc**, a simple scripting language that supports arithmetic expressions, variables, and control flow. Write `.calc` programs and run them directly from the command line.

🌐 **Live Demo:** [https://calc-interpreter.onrender.com](https://calc-interpreter.onrender.com)

---

## Features

- **Arithmetic Expressions** — Addition, subtraction, multiplication, division, and parenthesized expressions
- **Variables** — Declare and assign variables; use them in expressions
- **Control Flow** — `if` conditionals and loop constructs
- **Web Interface** — Run Calc programs directly in the browser
- **REPL Mode** — Interactive terminal for live expression evaluation

---

## Project Structure

```
Calc-Interpreter/
├── src/
│   └── main/
│       ├── java/com/calc/
│       │   ├── Main.java              # Entry point — web server + REPL + file mode
│       │   ├── CalcController.java    # REST API for web interface
│       │   ├── Interpreter.java       # Walks AST and evaluates expressions
│       │   ├── Tokenizer.java         # Tokenizes source input
│       │   ├── Parser.java            # Builds AST from tokens
│       │   ├── Environment.java       # Variable storage
│       │   ├── CalcException.java     # Error handling
│       │   └── ...
│       └── resources/static/
│           └── index.html             # Web interface
├── samples/
│   ├── program1.calc
│   ├── program2.calc
│   ├── program3.calc
│   └── program4.calc
├── Dockerfile
├── pom.xml
├── .gitignore
└── README.md
```

---

## Building & Running

### Prerequisites

- Java JDK 17 or later
- Maven 3.9+
- A terminal (PowerShell, bash, etc.)

### Run Web Interface

```powershell
mvn spring-boot:run
```

Then open [http://localhost:8080](http://localhost:8080) in your browser.

### Run a `.calc` File

```powershell
java -cp out Main samples/program1.calc
```

### Run REPL Mode

```powershell
java -cp out Main --repl
```

---

## Language Syntax

### Print

Use `>>` to print a value or expression.

```
>> "Hello, World!"
>> 42
```

### Variables

Assign a value to a variable with `:=`.

```
x := 10
y := x * 2 + 5
>> y          # => 25
```

### Arithmetic Expressions

Standard math operators are supported with the usual precedence rules.

```
>> 2 + 3 * 4       # => 14
>> (2 + 3) * 4     # => 20
>> 10 / 2 - 1      # => 4
```

### If Condition

```
score := 85
? score > 50 =>
>> "Pass"
```

### Loops

Use `@` followed by the number of iterations.

```
i := 1
@ 4 =>
>> i
i := i + 1
```

---

## Usage Examples

### Example 1 — Arithmetic (`samples/program1.calc`)

```
x := 10
y := 3
result := x + y * 2
>> result
```

Output:
```
16
```

### Example 2 — Strings (`samples/program2.calc`)

```
name := "Sitare"
>> name
>> "Hello from CALC"
```

Output:
```
Sitare
Hello from CALC
```

### Example 3 — If Condition (`samples/program3.calc`)

```
score := 85
? score > 50 =>
>> "Pass"
```

Output:
```
Pass
```

### Example 4 — Loop (`samples/program4.calc`)

```
i := 1
@ 4 =>
>> i
i := i + 1
```

Output:
```
1
2
3
4
```

---

## REPL Mode

Run without a file to enter interactive mode:

```powershell
java -cp out Main --repl
```

```
Calc Interpreter REPL
Type 'exit' to quit, 'load <file>' to run a file
─────────────────────────────────────────────
>>> x := 10
>>> >> x
10
>>> load samples/program1.calc
16
>>> exit
Goodbye!
```

---

## Troubleshooting

| Error | Cause | Fix |
|---|---|---|
| `ClassNotFoundException: Main` | Not compiled, or running from wrong directory | Compile with `javac src/*.java -d out` and run from project root |
| `Could not read file` | File path is relative to wrong directory | Always run `java` from the project root |
| `Unexpected token` | Syntax error in `.calc` file | Check your source file against the syntax guide above |
| `mvn: command not found` | Maven not installed or not in PATH | Install Maven and add to PATH |

---

## License

This project is open source. Feel free to use, modify, and distribute it.
