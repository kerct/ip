package task;

/**
 * Represents an event at a certain date or time.
 */
public class Event extends Task {
    private String at;

    /**
     * Creates an Event object with the given name, to be held at the given date or time.
     *
     * @param name Name of the event.
     * @param at Date or time of the event.
     */
    public Event(String name, String at) {
        super(name);
        this.at = at;
    }

    /**
     * Returns a string representation of the Event object.
     * Consists of the status of the event, its name and the event date or time.
     *
     * @return String representation.
     */
    @Override
    public String toString() {
        return "[E][" + getStatusIcon() + "] " + name + " (at: " + at + ")";
    }
}
