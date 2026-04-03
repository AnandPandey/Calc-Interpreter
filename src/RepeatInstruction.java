import java.util.Collections;
import java.util.List;
import java.util.Objects;

// Handles: @ <count> => <body>
final class RepeatInstruction implements Instruction {

    private final int               count;
    private final List<Instruction> body;

    public RepeatInstruction(int count, List<Instruction> body) {
        if (count < 0) {
            throw new CalcException(
                CalcException.Phase.RUNTIME, 0,
                "Loop count must be non-negative, got: " + count
            );
        }
        this.count = count;
        this.body  = Collections.unmodifiableList(
                        Objects.requireNonNull(body, "Body must not be null"));
    }

    @Override
    public void execute(Environment env) {
        for (int i = 0; i < count; i++) {
            for (Instruction instruction : body) {
                instruction.execute(env);
            }
        }
    }

    @Override
    public String toString() {
        return "RepeatInstruction(count=" + count + ", body=" + body.size() + " instructions)";
    }
}
