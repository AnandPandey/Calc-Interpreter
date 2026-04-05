package com.calc;
import java.util.Objects;

// Handles: x := <expression>
final class AssignInstruction implements Instruction {

    private final String     name;
    private final Expression expression;

    public AssignInstruction(String name, Expression expression) {
        this.name       = Objects.requireNonNull(name, "Variable name must not be null");
        this.expression = Objects.requireNonNull(expression, "Expression must not be null");
    }

    @Override
    public void execute(Environment env) {
        env.set(name, expression.evaluate(env));
    }

    @Override
    public String toString() {
        return "AssignInstruction(" + name + " := " + expression + ")";
    }
}
