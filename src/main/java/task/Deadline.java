package task;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    private String by;

    /**
     * Creates a Deadline object with the given name, to be completed by the given date or time.
     *
     * @param name Name of the task to be done.
     * @param by Date or time that the task is to be completed by.
     */
    public Deadline(String name, String by) {
        super(name);
        this.by = by;
    }

    /**
     * Returns a string representation of the Deadline object.
     * Consists of the status of the task, its name and the deadline.
     *
     * @return String representation.
     */
    @Override
    public String toString() {
        return "[D][" + getStatusIcon() + "] " + name + " (by: " + by + ")";
    }
}
