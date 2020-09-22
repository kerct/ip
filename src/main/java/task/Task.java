package task;

public class Task {
    private final String TICK_ICON = "\u2713";
    private final String CROSS_ICON = "\u2718";

    protected String name;
    protected boolean isDone;

    public Task(String name) {
        this.name = name;
        isDone = false;
    }

    public void markAsDone() {
        isDone = true;
    }

    protected String getStatusIcon() {
        return (isDone ? TICK_ICON : CROSS_ICON);
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + name;
    }
}
