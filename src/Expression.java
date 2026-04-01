// Evaluates to Double, String, or Boolean depending on expression type
public interface Expression {
    Object evaluate(Environment env);
}
