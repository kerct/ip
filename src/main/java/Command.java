/**
 * Represents the different commands recognised.
 */
public enum Command {
    TODO("todo"), DEADLINE("deadline"), EVENT("event"),
    LIST("list"), DONE("done"), DELETE("delete"),
    BY("/by"), AT("/at"),
    EXIT("bye"),
    INVALID("");

    private final String KEYWORD;

    Command(String keyword) {
        KEYWORD = keyword;
    }

    public String getKeyword() {
        return KEYWORD;
    }
}
