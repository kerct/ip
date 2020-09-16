import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Duke {
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String DOTTED_LINE =
            "\t----------------------------------------------------------";
    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";
    private static final String BY_COMMAND = "/by";
    private static final String AT_COMMAND = "/at";
    private static final String LIST_COMMAND = "list";
    private static final String DONE_COMMAND = "done";
    private static final String DELETE_COMMAND = "delete";
    private static final String EXIT_COMMAND = "bye";
    private static final String DIR = "data";
    private static final String FILEPATH = DIR + File.separator + "duke.txt";

    private static final ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        printWelcomeMessage();
        loadData();
        readInputs();
        printEndMessage();
    }

    private static void printWelcomeMessage() {
        System.out.println(LOGO);
        System.out.println(DOTTED_LINE);
        System.out.println("\tHello! I'm Duke");
        System.out.println("\tWhat can I do for you?");
        System.out.println(DOTTED_LINE);
    }

    private static void loadData() {
        try {
            File directory = new File(DIR);
            directory.mkdir();

            File file = new File(FILEPATH);
            // if file already exists, read it
            if (!file.createNewFile()) {
                Scanner reader = new Scanner(file);
                while (reader.hasNext()) {
                    handleInput(reader.nextLine(), false);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readInputs() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (!input.equalsIgnoreCase(EXIT_COMMAND)) {
            handleInput(input, true);
            input = scanner.nextLine();
        }
    }

    private static void addInputToFile(String input) throws IOException {
        // create a FileWriter in append mode
        FileWriter fw = new FileWriter(FILEPATH, true);
        fw.write(input + System.lineSeparator());
        fw.close();
    }

    private static void handleInput(String input, boolean finishSetup) {
        if (finishSetup) {
            System.out.println(DOTTED_LINE);
        }
        try {
            if (input.trim().equalsIgnoreCase(LIST_COMMAND)) {
                executeCommand(input, LIST_COMMAND, finishSetup);
            } else if (input.startsWith(DONE_COMMAND)) {
                executeCommand(input, DONE_COMMAND, finishSetup);
            } else if (input.startsWith(TODO_COMMAND)) {
                executeCommand(input, TODO_COMMAND, finishSetup);
            } else if (input.startsWith(DEADLINE_COMMAND)) {
                executeCommand(input, DEADLINE_COMMAND, finishSetup);
            } else if (input.startsWith(EVENT_COMMAND)) {
                executeCommand(input, EVENT_COMMAND, finishSetup);
            } else {
                System.out.println("\t☹ OOPS!!! I'm sorry, but I don't know what that means :(");
            }
        } catch (NumberFormatException e) {
            System.out.println("\tPlease enter the task number too!");
        } catch (InvalidIndexException e) {
            System.out.println("\t☹ OOPS!!! I can't find this task");
        } catch (EmptyNameException e) {
            System.out.println("\t☹ OOPS!!! The description of a todo cannot be empty.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (finishSetup) {
            System.out.println(DOTTED_LINE);
        }
    }

    private static void executeCommand(String input, String command, boolean finishSetup)
            throws InvalidIndexException, EmptyNameException, IOException {
        switch (command) {
        case LIST_COMMAND: {
            printList();
            break;
        }
        case DONE_COMMAND: {
            int index = getIndexFromInput(input, command);
            markTaskAsDone(index, finishSetup);
            if (finishSetup) {
                addInputToFile(input);
            }
            break;
        }
        case DELETE_COMMAND: {
            int index = getIndexFromInput(input, command);
            removeTask(index, finishSetup);
            if (finishSetup) {
                addInputToFile(input);
            }
            break;
        }
        case TODO_COMMAND: {
            String name = input.substring(TODO_COMMAND.length()).trim();
            if(name.length() == 0) {
                throw new EmptyNameException();
            }
            Task todo = new Todo(name);
            addToTaskList(todo, finishSetup);
            if (finishSetup) {
                addInputToFile(input);
            }
            break;
        }
        case DEADLINE_COMMAND: {
            int byIndex = input.indexOf(BY_COMMAND);
            if (byIndex == -1) {
                System.out.println("Please tell me when this is due!");
                return;
            }
            String name = input.substring(DEADLINE_COMMAND.length(), byIndex).trim();
            String by = input.substring(byIndex + BY_COMMAND.length()).trim();
            Task deadline = new Deadline(name, by);
            addToTaskList(deadline, finishSetup);
            if (finishSetup) {
                addInputToFile(input);
            }
            break;
        }
        case EVENT_COMMAND: {
            int atIndex = input.indexOf(AT_COMMAND);
            if (atIndex == -1) {
                System.out.println("Please tell me when is this event!");
                return;
            }
            String name = input.substring(EVENT_COMMAND.length(), atIndex).trim();
            String at = input.substring(atIndex + AT_COMMAND.length()).trim();
            Task event = new Event(name, at);
            addToTaskList(event, finishSetup);
            if (finishSetup) {
                addInputToFile(input);
            }
            break;
        }
        default: {
            System.out.println("Something went wrong here...");
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

    private static int getIndexFromInput(String input, String command)
            throws NumberFormatException, InvalidIndexException {
        int index = Integer.parseInt(input.substring(command.length()).trim()) - 1;
        if (index < 1 || index > tasks.size()) {
            throw new InvalidIndexException();
        }
        return index;
    }

    private static void markTaskAsDone(int index, boolean print) {
        Task task = tasks.get(index);
        task.markAsDone();
        if (print) {
            System.out.println("\tNice! I've marked this task as done: ");
            System.out.println("\t" + task.toString());
        }
    }

    private static void removeTask(int index, boolean print) {
        Task task = tasks.get(index);
        tasks.remove(index);
        if (print) {
            System.out.println("\tNoted. I've removed this task: ");
            printTaskAndTotal(task);
        }
    }

    private static void addToTaskList(Task task, boolean print) {
        tasks.add(task);
        if (print) {
            System.out.println("\tGot it. I've added this task:");
            printTaskAndTotal(task);
        }
    }

    private static void printTaskAndTotal(Task task) {
        System.out.println("\t\t" + task.toString());
        int total = tasks.size();
        if (total == 1) {
            System.out.println("\tNow you only have 1 task in the list!");
        } else {
            System.out.println("\tNow you have " + total + " tasks in the list.");
        }
    }

    private static void printEndMessage() {
        System.out.println(DOTTED_LINE);
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println(DOTTED_LINE);
    }
}
