package com.calc;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

// Handles: ? <condition> => <body>
final class IfInstruction implements Instruction {

    private final Expression        condition;
    private final List<Instruction> body;

    public IfInstruction(Expression condition, List<Instruction> body) {
        this.condition = Objects.requireNonNull(condition, "Condition must not be null");
        this.body      = Collections.unmodifiableList(
                            Objects.requireNonNull(body, "Body must not be null"));
    }

    @Override
    public void execute(Environment env) {
        Object result = condition.evaluate(env);
        if (result instanceof Boolean && (Boolean) result) {
            for (Instruction instruction : body) {
                instruction.execute(env);
            }
        }
    }

    @Override
    public String toString() {
        return "IfInstruction(condition=" + condition + ", body=" + body.size() + " instructions)";
    }
}
