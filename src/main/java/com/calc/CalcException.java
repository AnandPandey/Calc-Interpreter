// Single exception type for all interpreter errors (tokenizer, parser, runtime)
// Format: [TOKENIZER] line 3: Unexpected character '@'
package com.calc;
public class CalcException extends RuntimeException {

    public enum Phase { TOKENIZER, PARSER, RUNTIME }

    private final Phase phase;
    private final int   line;

    public CalcException(Phase phase, int line, String message) {
        super(String.format("[%s] line %d: %s", phase.name(), line, message));
        this.phase = phase;
        this.line  = line;
    }

    public Phase getPhase() { return phase; }
    public int   getLine()  { return line;  }
}