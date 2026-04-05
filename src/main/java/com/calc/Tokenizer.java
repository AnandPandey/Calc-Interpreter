package com.calc;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Step 1 of pipeline: converts raw source code into a List<Token>
public class Tokenizer {

    private final String source;
    private int pos;
    private int line;

    public Tokenizer(String source) {
        this.source = source;
        this.pos    = 0;
        this.line   = 1;
    }

    // Returns unmodifiable token list, always ending with EOF
    public List<Token> tokenize() {
        List<Token> tokens = new ArrayList<>();

        while (!isAtEnd()) {
            char c = advance();

            if (c == ' ' || c == '\t' || c == '\r') continue;

            if (c == '\n') {
                tokens.add(makeToken(TokenType.NEWLINE, "\\n"));
                line++;
                continue;
            }

            if (c == '#') { skipToEndOfLine(); continue; }

            if (c == ':') { tokens.add(readAssign());            continue; }
            if (c == '>') { tokens.add(readGreaterOrPrint());    continue; }
            if (c == '=') { tokens.add(readArrowOrEquals());     continue; }

            if (c == '+') { tokens.add(makeToken(TokenType.PLUS,  "+")); continue; }
            if (c == '-') { tokens.add(makeToken(TokenType.MINUS, "-")); continue; }
            if (c == '*') { tokens.add(makeToken(TokenType.STAR,  "*")); continue; }
            if (c == '/') { tokens.add(makeToken(TokenType.SLASH, "/")); continue; }
            if (c == '<') { tokens.add(makeToken(TokenType.LESS,  "<")); continue; }
            if (c == '?') { tokens.add(makeToken(TokenType.IF,    "?")); continue; }
            if (c == '@') { tokens.add(makeToken(TokenType.LOOP,  "@")); continue; }

            if (c == '"')                              { tokens.add(readString());       continue; }
            if (Character.isDigit(c))                  { tokens.add(readNumber(c));      continue; }
            if (Character.isLetter(c) || c == '_')     { tokens.add(readIdentifier(c)); continue; }

            throw new CalcException(
                CalcException.Phase.TOKENIZER, line,
                "Unexpected character '" + c + "'"
            );
        }

        tokens.add(makeToken(TokenType.EOF, ""));
        return Collections.unmodifiableList(tokens);
    }

    private boolean isAtEnd()  { return pos >= source.length(); }
    private char peek()        { return isAtEnd() ? '\0' : source.charAt(pos); }
    private char advance()     { return source.charAt(pos++); }
    private Token makeToken(TokenType type, String value) { return new Token(type, value, line); }

    private void skipToEndOfLine() {
        while (!isAtEnd() && peek() != '\n') advance();
    }

    // ':' already consumed — expects '=' next
    private Token readAssign() {
        if (!isAtEnd() && peek() == '=') {
            advance();
            return makeToken(TokenType.ASSIGN, ":=");
        }
        throw new CalcException(
            CalcException.Phase.TOKENIZER, line,
            "Expected '=' after ':' — did you mean ':='?"
        );
    }

    // First '>' already consumed
    private Token readGreaterOrPrint() {
        if (!isAtEnd() && peek() == '>') {
            advance();
            return makeToken(TokenType.PRINT, ">>");
        }
        return makeToken(TokenType.GREATER, ">");
    }

    // First '=' already consumed — lone '=' is invalid in CALC
    private Token readArrowOrEquals() {
        if (!isAtEnd() && peek() == '>') {
            advance();
            return makeToken(TokenType.ARROW, "=>");
        }
        if (!isAtEnd() && peek() == '=') {
            advance();
            return makeToken(TokenType.EQUAL_EQUAL, "==");
        }
        throw new CalcException(
            CalcException.Phase.TOKENIZER, line,
            "Unexpected '=' — use ':=' to assign, '=>' for blocks, '==' for equality"
        );
    }

    // Opening '"' already consumed
    private Token readString() {
        StringBuilder sb = new StringBuilder();
        while (!isAtEnd() && peek() != '"') {
            if (peek() == '\n') line++;
            sb.append(advance());
        }
        if (isAtEnd()) throw new CalcException(
            CalcException.Phase.TOKENIZER, line,
            "Unterminated string — missing closing '\"'"
        );
        advance();
        return makeToken(TokenType.STRING, sb.toString());
    }

    // First digit already consumed
    private Token readNumber(char firstDigit) {
        StringBuilder sb = new StringBuilder(String.valueOf(firstDigit));
        while (!isAtEnd() && (Character.isDigit(peek()) || peek() == '.')) {
            sb.append(advance());
        }
        return makeToken(TokenType.NUMBER, sb.toString());
    }

    // First char already consumed — all words are identifiers in CALC
    private Token readIdentifier(char firstChar) {
        StringBuilder sb = new StringBuilder(String.valueOf(firstChar));
        while (!isAtEnd() && (Character.isLetterOrDigit(peek()) || peek() == '_')) {
            sb.append(advance());
        }
        return makeToken(TokenType.IDENTIFIER, sb.toString());
    }
}