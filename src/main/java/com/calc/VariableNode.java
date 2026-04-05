package com.calc;
// Leaf node — variable reference e.g. x, result
final class VariableNode implements Expression {

    private final String name;

    public VariableNode(String name) {
        this.name = name;
    }

    @Override
    public Object evaluate(Environment env) {
        return env.get(name);
    }

    @Override
    public String toString() {
        return "VariableNode(" + name + ")";
    }
}
