import java.util.HashMap;
import java.util.Map;

/**
 * Environment — the variable store for the entire program execution.
 *
 * Maps variable names (String) to their current values (Double or String).
 * Every instruction shares ONE Environment instance, allowing a variable
 * set on line 1 to be visible on line 10.
 *
 * DESIGN PRINCIPLE: Encapsulation
 *   The internal HashMap is completely hidden. Callers use set() and get()
 *   only. If the storage mechanism ever changed (e.g. to a database), only
 *   this class would need to change — nothing else.
 *
 * DESIGN PRINCIPLE: Single Responsibility Principle (SRP)
 *   Environment has exactly one job: store and retrieve variable values.
 *   It contains no expression evaluation logic and no printing logic.
 *
 * DESIGN PRINCIPLE: Fail Fast
 *   get() throws a CalcException immediately if the variable was never
 *   defined, giving the user a clear error message with the variable name.
 *
 * Example walkthrough:
 *   env.set("x", 10.0)         →  stores x = 10.0
 *   env.set("y", 3.0)          →  stores y = 3.0
 *   env.set("result", 16.0)    →  stores result = 16.0
 *   env.get("result")          →  returns 16.0
 *   env.get("z")               →  throws CalcException (not defined)
 */
public class Environment {

    // The internal variable store — name → current value
    private final Map<String, Object> variables = new HashMap<>();

    // ── Public API ────────────────────────────────────────────────────────────

    /**
     * Store or update a variable's value.
     * If the variable already exists, its value is overwritten.
     *
     * @param name   the variable name (e.g. "x")
     * @param value  the value to store (Double or String)
     */
    public void set(String name, Object value) {
        variables.put(name, value);
    }

    /**
     * Retrieve the current value of a variable.
     *
     * @param  name  the variable name to look up
     * @return       the current value (Double or String)
     * @throws CalcException if the variable was never defined
     */
    public Object get(String name) {
        if (!variables.containsKey(name)) {
            throw new CalcException(
                CalcException.Phase.RUNTIME, 0,
                "Variable not defined: '" + name + "'"
                + " — did you forget to assign it with ':='?"
            );
        }
        return variables.get(name);
    }

    /**
     * Returns true if the variable has been defined.
     * Useful for optional checks without triggering an exception.
     */
    public boolean isDefined(String name) {
        return variables.containsKey(name);
    }
}
