package task;

public class Task {
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
        //return tick or X symbols
        return (isDone ? "\u2713" : "\u2718");
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + name;
    }
}
