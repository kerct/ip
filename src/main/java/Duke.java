import java.util.Scanner;

public class Duke {
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String DOTTED_LINE = "\t-----------------------------------------";
    private static final int MAX_TASKS = 100;
    private static final String EXIT_COMMAND = "bye";
    private static final String LIST_COMMAND = "list";
    private static final String DONE_COMMAND = "done";
    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";
    private static final String BY_COMMAND = "/by";
    private static final String AT_COMMAND = "/at";

    private static final Task[] tasks = new Task[MAX_TASKS];
    private static int numOfTasks = 0;

    public static void main(String[] args) {
        printWelcomeMessage();

        Scanner in = new Scanner(System.in);
        String input = in.nextLine();

        while (!input.equalsIgnoreCase(EXIT_COMMAND)) {
            System.out.println(DOTTED_LINE);
            if (input.equalsIgnoreCase(LIST_COMMAND)) {
                handleInput(input, LIST_COMMAND);
            } else if (input.startsWith(DONE_COMMAND)) {
                handleInput(input, DONE_COMMAND);
            } else if (input.startsWith(TODO_COMMAND)) {
                handleInput(input, TODO_COMMAND);
            } else if (input.startsWith(DEADLINE_COMMAND)) {
                handleInput(input, DEADLINE_COMMAND);
            } else if (input.startsWith(EVENT_COMMAND)) {
                handleInput(input, EVENT_COMMAND);
            } else {
                handleInput(input, "");
            }
            System.out.println(DOTTED_LINE);
            input = in.nextLine();
        }

        printEndMessage();
    }

    private static void printWelcomeMessage() {
        System.out.println(LOGO);
        System.out.println(DOTTED_LINE);
        System.out.println("\tHello! I'm Duke");
        System.out.println("\tWhat can I do for you?");
        System.out.println(DOTTED_LINE);
    }

    private static void handleInput(String input, String command) {
        switch (command) {
        case LIST_COMMAND: {
            printList();
            break;
        }
        case DONE_COMMAND: {
            int index = Integer.parseInt(input.substring(command.length()).trim());
            Task task = tasks[index - 1];
            markTaskAsDone(task);
            break;
        }
        case TODO_COMMAND: {
            String name = input.substring(TODO_COMMAND.length()).trim();
            Task todo = new Todo(name);
            addToTaskList(todo);
            break;
        }
        case DEADLINE_COMMAND: {
            int byIndex = input.indexOf(BY_COMMAND);
            String name = input.substring(DEADLINE_COMMAND.length(), byIndex).trim();
            String by = input.substring(byIndex + BY_COMMAND.length()).trim();
            Task deadline = new Deadline(name, by);
            addToTaskList(deadline);
            break;
        }
        case EVENT_COMMAND: {
            int atIndex = input.indexOf(AT_COMMAND);
            String name = input.substring(EVENT_COMMAND.length(), atIndex).trim();
            String at = input.substring(atIndex + AT_COMMAND.length()).trim();
            Task event = new Event(name, at);
            addToTaskList(event);
            break;
        }
        default: {
            Task task = new Task(input);
            addToTaskList(task);
            break;
        }
        }
    }

    private static void printList() {
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < numOfTasks; i++) {
            Task task = tasks[i];
            System.out.println("\t" + (i + 1) + ". " + task.toString());
        }
    }

    private static void markTaskAsDone(Task task) {
        task.markAsDone();
        System.out.println("\tNice! I've marked this task as done: ");
        System.out.println("\t" + task.toString());
    }

    private static void addToTaskList(Task task) {
        tasks[numOfTasks] = task;
        numOfTasks++;
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t\t" + task.toString());
        System.out.println("\tNow you have " + numOfTasks + " tasks in the list.");
    }

    private static void printEndMessage() {
        System.out.println(DOTTED_LINE);
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println(DOTTED_LINE);
    }
}
