package interpreter.control_flow;

public class Continue extends RuntimeException {
    private final static Continue instance = new Continue();

    private Continue() {
    }

    public static Continue instance() {
        return instance;
    }

}