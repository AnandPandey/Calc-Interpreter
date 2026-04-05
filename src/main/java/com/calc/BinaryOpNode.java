// Internal node — applies an operator to two sub-expressions
// Arithmetic (+ - * /) → Double,  Comparisons (> < ==) → Boolean
package com.calc;
final class BinaryOpNode implements Expression {

    private final Expression left;
    private final String     operator;
    private final Expression right;

    public BinaryOpNode(Expression left, String operator, Expression right) {
        this.left     = left;
        this.operator = operator;
        this.right    = right;
    }

    @Override
    public Object evaluate(Environment env) {
        Object leftVal  = left.evaluate(env);
        Object rightVal = right.evaluate(env);

        switch (operator) {
            case "+": return toDouble(leftVal) + toDouble(rightVal);
            case "-": return toDouble(leftVal) - toDouble(rightVal);
            case "*": return toDouble(leftVal) * toDouble(rightVal);
            case "/": {
                double divisor = toDouble(rightVal);
                if (divisor == 0) throw new CalcException(
                    CalcException.Phase.RUNTIME, 0, "Division by zero");
                return toDouble(leftVal) / divisor;
            }
            case ">":  return toDouble(leftVal) >  toDouble(rightVal);
            case "<":  return toDouble(leftVal) <  toDouble(rightVal);
            case "==": return toDouble(leftVal) == toDouble(rightVal);

            default:
                throw new CalcException(
                    CalcException.Phase.RUNTIME, 0,
                    "Unknown operator: " + operator
                );
        }
    }

    // Throws a clear error if value is not a number
    private double toDouble(Object val) {
        if (val instanceof Double) return (Double) val;
        throw new CalcException(
            CalcException.Phase.RUNTIME, 0,
            "Expected a number but got: '" + val + "' (" + val.getClass().getSimpleName() + ")"
        );
    }

    @Override
    public String toString() {
        return "BinaryOpNode(" + left + " " + operator + " " + right + ")";
    }
}
