import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String DOTTED_LINE =
            "\t----------------------------------------------------------";
    private static final String EXIT_COMMAND = "bye";
    private static final String LIST_COMMAND = "list";
    private static final String DONE_COMMAND = "done";
    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";
    private static final String BY_COMMAND = "/by";
    private static final String AT_COMMAND = "/at";

    private static final ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        printWelcomeMessage();
        Scanner scanner = new Scanner(System.in);
        handleInputs(scanner);
        printEndMessage();
    }

    private static void printWelcomeMessage() {
        System.out.println(LOGO);
        System.out.println(DOTTED_LINE);
        System.out.println("\tHello! I'm Duke");
        System.out.println("\tWhat can I do for you?");
        System.out.println(DOTTED_LINE);
    }

    private static void handleInputs(Scanner scanner) {
        String input = scanner.nextLine();
        while (!input.equalsIgnoreCase(EXIT_COMMAND)) {
            System.out.println(DOTTED_LINE);
            try {
                if (input.equalsIgnoreCase(LIST_COMMAND)) {
                    executeCommand(input, LIST_COMMAND);
                } else if (input.startsWith(DONE_COMMAND)) {
                    executeCommand(input, DONE_COMMAND);
                } else if (input.startsWith(TODO_COMMAND)) {
                    executeCommand(input, TODO_COMMAND);
                } else if (input.startsWith(DEADLINE_COMMAND)) {
                    executeCommand(input, DEADLINE_COMMAND);
                } else if (input.startsWith(EVENT_COMMAND)) {
                    executeCommand(input, EVENT_COMMAND);
                } else {
                    System.out.println("\t☹ OOPS!!! I'm sorry, but I don't know what that means :(");
                }
            } catch (EmptyNameException e) {
                System.out.println("\t☹ OOPS!!! The description of a todo cannot be empty.");
            }
            System.out.println(DOTTED_LINE);
            input = scanner.nextLine();
        }
    }

    private static void executeCommand(String input, String command) throws EmptyNameException {
        switch (command) {
        case LIST_COMMAND: {
            printList();
            break;
        }
        case DONE_COMMAND: {
            int index = Integer.parseInt(input.substring(command.length()).trim());
            Task task = tasks.get(index - 1);
            markTaskAsDone(task);
            break;
        }
        case TODO_COMMAND: {
            String name = input.substring(TODO_COMMAND.length()).trim();
            if(name.length() == 0) {
                throw new EmptyNameException();
            }
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
        int numOfTasks = tasks.size();
        for (int i = 0; i < numOfTasks; i++) {
            Task task = tasks.get(i);
            System.out.println("\t" + (i + 1) + ". " + task.toString());
        }
    }

    private static void markTaskAsDone(Task task) {
        task.markAsDone();
        System.out.println("\tNice! I've marked this task as done: ");
        System.out.println("\t" + task.toString());
    }

    private static void addToTaskList(Task task) {
        tasks.add(task);
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t\t" + task.toString());
        System.out.println("\tNow you have " + tasks.size() + " tasks in the list.");
    }

    private static void printEndMessage() {
        System.out.println(DOTTED_LINE);
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println(DOTTED_LINE);
    }
}
