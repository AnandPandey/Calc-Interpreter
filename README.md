
# Calc Interpreter

A Java-based interpreter for **Calc**, a simple scripting language that supports arithmetic expressions, variables, and control flow. Write `.calc` programs and run them directly from the command line.

---

## Features

- **Arithmetic Expressions** — Addition, subtraction, multiplication, division, and parenthesized expressions
- **Variables** — Declare and assign variables; use them in expressions
- **Control Flow** — `if`/`else` conditionals and loop constructs

---

## Project Structure

```
Calc-Interpreter/
├── src/
│   ├── Main.java          # Entry point — reads and runs .calc files
│   ├── Lexer.java         # Tokenizes source input
│   ├── Parser.java        # Builds AST from tokens
│   ├── Interpreter.java   # Walks AST and evaluates expressions
│   └── ...
├── samples/
│   ├── program1.calc
│   ├── program2.calc
│   └── ...
└── README.md
```

---

## Building & Running

### Prerequisites

- Java JDK 8 or later
- A terminal (PowerShell, bash, etc.)

### Compile

From the project root:

```powershell
javac src/*.java -d out
```

This compiles all source files and places the `.class` files into the `out/` directory.

### Run

```powershell
java -cp out Main samples/program1.calc
```

> **Note:** Always run from the project root so that relative file paths resolve correctly.

---

## Language Syntax

### Arithmetic Expressions

Standard math operators are supported with the usual precedence rules.

```
2 + 3 * 4       # => 14
(2 + 3) * 4     # => 20
10 / 2 - 1      # => 4
```

### Variables

Assign a value to a variable with `=`. Variables can be used in any expression after assignment.

```
x = 10
y = x * 2 + 5
print y          # => 25
```

### If / Else

```
x = 15

if x > 10 {
    print "x is greater than 10"
} else {
    print "x is 10 or less"
}
```

### Loops

```
i = 0
while i < 5 {
    print i
    i = i + 1
}
```

---

## Usage Examples

### Example 1 — Basic Arithmetic (`samples/program1.calc`)

```
a = 6
b = 7
print a * b      # => 42
```

Run it:

```powershell
java -cp out Main samples/program1.calc
```

Output:
```
42
```

### Example 2 — Countdown Loop

```
n = 5
while n > 0 {
    print n
    n = n - 1
}
print "Liftoff!"
```

Output:
```
5
4
3
2
1
Liftoff!
```

### Example 3 — Conditional Logic

```
score = 72

if score >= 90 {
    print "A"
} else if score >= 80 {
    print "B"
} else if score >= 70 {
    print "C"
} else {
    print "F"
}
```

Output:
```
C
```

---

## Troubleshooting

| Error | Cause | Fix |
|---|---|---|
| `ClassNotFoundException: Main` | Not compiled, or running from wrong directory | Compile with `javac src/*.java -d out` and run from project root |
| `Could not read file` | File path is relative to wrong directory | Always run `java` from the project root |
| `Unexpected token` | Syntax error in `.calc` file | Check your source file against the syntax guide above |

---

## License

This project is open source. Feel free to use, modify, and distribute it.
