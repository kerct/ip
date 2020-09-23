import task.Task;

/**
 * Deals with the display of messages to the user.
 */
public class Ui {
    private final String DOTTED_LINE =
            "\t----------------------------------------------------------";

    /**
     * Creates a new Ui object and prints out the logo.
     */
    public Ui() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
    }

    /**
     * Displays a greeting message to welcome the user.
     */
    public void printWelcomeMessage() {
        System.out.println(DOTTED_LINE);
        System.out.println("\tHello! I'm Duke");
        System.out.println("\tWhat can I do for you?");
        System.out.println(DOTTED_LINE);
    }

    /**
     * Displays an ending message to say goodbye to the user.
     */
    public void printEndMessage() {
        System.out.println(DOTTED_LINE);
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println(DOTTED_LINE);
    }

    /**
     * Formats and displays the given error message.
     *
     * @param errorMessage The error message to be printed out.
     */
    public void printErrorMessage(String errorMessage) {
        System.out.println(DOTTED_LINE);
        System.out.println("\tERROR");
        System.out.println("\t" + errorMessage);
        System.out.println(DOTTED_LINE);
    }

    /**
     * Prints a dotted line which acts as a line break to distinguish between sections.
     */
    public void printDottedLine() {
        System.out.println(DOTTED_LINE);
    }

    /**
     * Displays a notification that the given Task is marked as done.
     *
     * @param task Task that was marked as done.
     */
    public void printTaskMarkedAsDone(Task task) {
        System.out.println("\tNice! I've marked this task as done: ");
        System.out.println("\t" + task.toString());
    }

    /**
     * Displays a notification that the given Task is removed,
     * and prints out the total number of tasks left.
     *
     * @param task Task that was removed.
     * @param numOfTasks Total number of tasks left.
     */
    public void printTaskRemoved(Task task, int numOfTasks) {
        System.out.println("\tNoted. I've removed this task: ");
        printTaskAndTotal(task, numOfTasks);
    }

    /**
     * Displays a notification to show that the Task was added.
     *
     * @param task Task that was just added.
     * @param numOfTasks Total number of tasks in the list after adding.
     */
    public void printTaskAdded(Task task, int numOfTasks) {
        System.out.println("\tGot it. I've added this task:");
        printTaskAndTotal(task, numOfTasks);
    }

    private void printTaskAndTotal(Task task, int numOfTasks) {
        System.out.println("\t\t" + task.toString());
        if (numOfTasks == 1) {
            System.out.println("\tNow you only have 1 task in the list!");
        } else {
            System.out.println("\tNow you have " + numOfTasks + " tasks in the list.");
        }
    }

    public void printTaskList(TaskList tasks) {
        if (tasks.getTotal() == 0) {
            System.out.println("\tThere are no tasks in your list!");
        }
        else {
            tasks.printList("Here are the tasks in your list:");
        }
    }

    public void printMatchingTasks(TaskList tasks) {
        if (tasks.getTotal() == 0) {
            System.out.println("\tNo tasks found!");
        }
        else {
            tasks.printList("Here are the matching tasks in your list:");
        }
    }
}
