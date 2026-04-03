import java.util.Objects;

// Handles: >> <expression>
// Whole number doubles (16.0) are printed as integers (16)
final class PrintInstruction implements Instruction {

    private final Expression expression;

    public PrintInstruction(Expression expression) {
        this.expression = Objects.requireNonNull(expression, "Expression must not be null");
    }

    @Override
    public void execute(Environment env) {
        System.out.println(formatValue(expression.evaluate(env)));
    }

    private String formatValue(Object value) {
        if (value instanceof Double) {
            double d = (Double) value;
            if (d == Math.floor(d) && !Double.isInfinite(d)) {
                return String.valueOf((long) d); // 16.0 → "16"
            }
            return String.valueOf(d);
        }
        return String.valueOf(value);
    }

    @Override
    public String toString() {
        return "PrintInstruction(" + expression + ")";
    }
}
