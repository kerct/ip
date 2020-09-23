package task;

public class Task {
    private final String TICK_ICON = "\u2713";
    private final String CROSS_ICON = "\u2718";

    protected String name;
    protected boolean isDone;

    /**
     * Creates a Task object with the given name.
     * By default, the task is not completed.
     *
     * @param name Name of the task.
     */
    public Task(String name) {
        this.name = name;
        isDone = false;
    }

    /**
     * Marks a task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Returns the string representing the icon of the task status.
     * A completed task is represented by a tick,
     * while a task that is not done is represented by a cross.
     *
     * @return The unicode representation of the status icon.
     */
    protected String getStatusIcon() {
        return (isDone ? TICK_ICON : CROSS_ICON);
    }

    /**
     * Returns a string representation of the Task object.
     * Consists of the task status and name.
     *
     * @return String representation.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + name;
    }

    /**
     * Returns the name of the task.
     *
     * @return Name of the task.
     */
    public String getName() {
        return name;
    }
}
