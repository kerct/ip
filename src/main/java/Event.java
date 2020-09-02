public class Event extends Task {
    private String at;

    public Event(String name, String at) {
        super(name);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E][" + getStatusIcon() + "] " + name + " (at: " + at + ")";
    }
}