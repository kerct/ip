import exception.EmptyNameException;
import exception.InvalidIndexException;
import exception.MissingArgumentException;
import task.Task;
import task.Todo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;
    private Parser parser;

    /**
     * Creates a Duke instance with data loaded from and stored at the specified file path.
     *
     * @param filePath File path where data will be loaded from and stored.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList();
        parser = new Parser();
        try {
            Scanner reader = storage.getReader();
            while (reader.hasNext()) {
                String input = reader.nextLine();
                Command command = parser.identifyCommand(input);
                handleCommand(input, command, false);
            }
        } catch (FileNotFoundException e) {
            ui.printErrorMessage(e.getMessage());
        }
    }

    /**
     * Starts the Duke program where users will be prompted for inputs.
     */
    private void run() {
        ui.printWelcomeMessage();
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        Command command = parser.identifyCommand(input);
        while (command != Command.EXIT) {
            handleCommand(input, command, true);
            input = scanner.nextLine();
            command = parser.identifyCommand(input);
        }
        ui.printEndMessage();
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

    private void handleCommand(String input, Command command, boolean toPrint) {
        try {
            if (toPrint) {
                ui.printDottedLine();
            }
            executeCommand(input, command, toPrint);
        } catch (NumberFormatException e) {
            System.out.println("\tPlease enter the task number too!");
        } catch (InvalidIndexException e) {
            System.out.println("\t☹ OOPS!!! I can't find this task");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (toPrint) {
                ui.printDottedLine();
            }
        }
    }

    private void executeCommand(String input, Command command, boolean toPrint)
            throws InvalidIndexException, IOException {
        switch (command) {
        case LIST:
            ui.printTaskList(tasks);
            break;
        case DONE: {
            int index = parser.getIndexFromInput(input, command, tasks.getTotal());
            Task task = tasks.markTaskAsDone(index);
            if (toPrint) {
                ui.printTaskMarkedAsDone(task);
                storage.appendToFile(input);
            }
            break;
        }
        case DELETE: {
            int index = parser.getIndexFromInput(input, command, tasks.getTotal());
            Task task = tasks.removeTask(index);
            if (toPrint) {
                ui.printTaskRemoved(task, tasks.getTotal());
                storage.appendToFile(input);
            }
            break;
        }
        case TODO:
            try {
                String name = parser.getNameFromInput(input, command);
                Task todo = new Todo(name);
                tasks.addToTaskList(todo);
                if (toPrint) {
                    ui.printTaskAdded(todo, tasks.getTotal());
                    storage.appendToFile(input);
                }
            } catch (EmptyNameException e) {
                System.out.println("\t☹ OOPS!!! The description of a todo cannot be empty.");
            }
            break;
        case DEADLINE:
            try {
                Task deadline = parser.getDeadlineFromInput(input);
                tasks.addToTaskList(deadline);
                if (toPrint) {
                    ui.printTaskAdded(deadline, tasks.getTotal());
                    storage.appendToFile(input);
                }
            } catch (MissingArgumentException e) {
                System.out.println("\tPlease tell me when this is due!");
            }
            break;
        case EVENT:
            try {
                Task event = parser.getEventFromInput(input);
                tasks.addToTaskList(event);
                if (toPrint) {
                    ui.printTaskAdded(event, tasks.getTotal());
                    storage.appendToFile(input);
                }
            } catch (MissingArgumentException e) {
                System.out.println("\tPlease tell me when is this event!");
            }
            break;
        case FIND:
            try {
                String searchTerm = parser.getNameFromInput(input, command);
                TaskList matchingTasks = tasks.findTasks(searchTerm);
                ui.printMatchingTasks(matchingTasks);
            } catch (EmptyNameException e) {
                System.out.println("\tWhat do you want to find?");
            }
            break;
        case INVALID:
            System.out.println("\t☹ OOPS!!! I'm sorry, but I don't know what that means :(");
            break;
        default:
            System.out.println("\tSomething went wrong here...");
            break;
        }
    }
}
