import task.Task;

public class Ui {
    private final String DOTTED_LINE =
            "\t----------------------------------------------------------";

    public Ui() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
    }

    public void printWelcomeMessage() {
        System.out.println(DOTTED_LINE);
        System.out.println("\tHello! I'm Duke");
        System.out.println("\tWhat can I do for you?");
        System.out.println(DOTTED_LINE);
    }

    public void printEndMessage() {
        System.out.println(DOTTED_LINE);
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println(DOTTED_LINE);
    }

    public void printErrorMessage(String errorMessage) {
        System.out.println(DOTTED_LINE);
        System.out.println("\tERROR");
        System.out.println("\t" + errorMessage);
        System.out.println(DOTTED_LINE);
    }

    public void printDottedLine() {
        System.out.println(DOTTED_LINE);
    }

    public void printTaskMarkedAsDone(Task task) {
        System.out.println("\tNice! I've marked this task as done: ");
        System.out.println("\t" + task.toString());
    }

    public void printTaskRemoved(Task task, int numOfTasks) {
        System.out.println("\tNoted. I've removed this task: ");
        printTaskAndTotal(task, numOfTasks);
    }

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
}
