public enum Command {
    TODO("todo"), DEADLINE("deadline"), EVENT("event"),
    LIST("list"), DONE("done"), DELETE("delete"), FIND("find"),
    BY("/by"), AT("/at"),
    EXIT("bye"),
    INVALID("");

    private final String KEYWORD;

    private Command(String keyword) {
        KEYWORD = keyword;
    }

    public String getKeyword() {
        return KEYWORD;
    }
}
