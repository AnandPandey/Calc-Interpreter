package com.calc;
import java.util.Objects;

// Immutable value object representing one labelled piece of source code.
// Example: "x := 10" → Token(IDENTIFIER,"x",1), Token(ASSIGN,":=",1), Token(NUMBER,"10",1)
public final class Token {

    private final TokenType type;
    private final String    value;
    private final int       line;

    public Token(TokenType type, String value, int line) {
        this.type  = Objects.requireNonNull(type,  "TokenType must not be null");
        this.value = Objects.requireNonNull(value, "Token value must not be null");
        this.line  = line;
    }

    public TokenType getType()  { return type;  }
    public String    getValue() { return value; }
    public int       getLine()  { return line;  }

    // Shorthand for token.getType() == TokenType.X
    public boolean isType(TokenType t) {
        return this.type == t;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Token)) return false;
        Token other = (Token) obj;
        return type == other.type
            && value.equals(other.value)
            && line == other.line;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, value, line);
    }

    @Override
    public String toString() {
        return String.format("Token(%-12s  %-8s  line=%d)",
            type, "\"" + value + "\"", line);
    }
}