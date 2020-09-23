package task;

/**
 * Represents a task to be done.
 */
public class Todo extends Task {
    /**
     * Creates a Todo object with the given name.
     *
     * @param name Name of the todo.
     */
    public Todo(String name) {
        super(name);
    }

    /**
     * Returns a string representation of the Todo object.
     * Consists of the status of the todo and its name.
     *
     * @return String representation.
     */
    @Override
    public String toString() {
        return "[T][" + getStatusIcon() + "] " + name;
    }
}
