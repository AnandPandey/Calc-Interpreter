// Defines every token type in the CALC language vocabulary
public enum TokenType {

    // CALC symbols
    ASSIGN,         // :=
    PRINT,          // >>
    IF,             // ?
    LOOP,           // @
    ARROW,          // =>

    // Literals
    NUMBER,         // 10  3.14
    STRING,         // "hello"
    IDENTIFIER,     // x  result  score

    // Arithmetic
    PLUS,           // +
    MINUS,          // -
    STAR,           // *
    SLASH,          // /

    // Comparison
    GREATER,        // >
    LESS,           // 
    EQUAL_EQUAL,    // ==

    // Structure
    NEWLINE,
    EOF
}