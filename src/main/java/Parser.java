import exception.EmptyNameException;
import exception.InvalidIndexException;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.io.IOException;

public class Parser {
    private final String TODO_COMMAND = "todo";
    private final String DEADLINE_COMMAND = "deadline";
    private final String EVENT_COMMAND = "event";
    private final String BY_COMMAND = "/by";
    private final String AT_COMMAND = "/at";
    private final String LIST_COMMAND = "list";
    private final String DONE_COMMAND = "done";
    private final String DELETE_COMMAND = "delete";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Parser(Storage storage, TaskList tasks, Ui ui) {
        this.storage = storage;
        this.tasks = tasks;
        this.ui = ui;
    }

    public TaskList getTasks() {
        return tasks;
    }

    public void handleInput(String input, boolean hasLoaded) {
        if (hasLoaded) {
            ui.printDottedLine();
        }
        try {
            if (input.trim().equalsIgnoreCase(LIST_COMMAND)) {
                executeCommand(input, LIST_COMMAND, hasLoaded);
            } else if (input.startsWith(DONE_COMMAND)) {
                executeCommand(input, DONE_COMMAND, hasLoaded);
            } else if (input.startsWith(DELETE_COMMAND)) {
                executeCommand(input, DELETE_COMMAND, hasLoaded);
            } else if (input.startsWith(TODO_COMMAND)) {
                executeCommand(input, TODO_COMMAND, hasLoaded);
            } else if (input.startsWith(DEADLINE_COMMAND)) {
                executeCommand(input, DEADLINE_COMMAND, hasLoaded);
            } else if (input.startsWith(EVENT_COMMAND)) {
                executeCommand(input, EVENT_COMMAND, hasLoaded);
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
        if (hasLoaded) {
            ui.printDottedLine();
        }
    }

    private void executeCommand(String input, String command, boolean finishSetup)
            throws InvalidIndexException, EmptyNameException, IOException {
        switch (command) {
        case LIST_COMMAND: {
            tasks.printList();
            break;
        }
        case DONE_COMMAND: {
            int index = getIndexFromInput(input, command);
            tasks.markTaskAsDone(index, finishSetup);
            if (finishSetup) {
                storage.appendToFile(input);
            }
            break;
        }
        case DELETE_COMMAND: {
            int index = getIndexFromInput(input, command);
            tasks.removeTask(index, finishSetup);
            if (finishSetup) {
                storage.appendToFile(input);
            }
            break;
        }
        case TODO_COMMAND: {
            String name = input.substring(TODO_COMMAND.length()).trim();
            if(name.length() == 0) {
                throw new EmptyNameException();
            }
            Task todo = new Todo(name);
            tasks.addToTaskList(todo, finishSetup);
            if (finishSetup) {
                storage.appendToFile(input);
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
            tasks.addToTaskList(deadline, finishSetup);
            if (finishSetup) {
                storage.appendToFile(input);
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
            tasks.addToTaskList(event, finishSetup);
            if (finishSetup) {
                storage.appendToFile(input);
            }
            break;
        }
        default: {
            System.out.println("Something went wrong here...");
            break;
        }
        }
    }

    private int getIndexFromInput(String input, String command)
            throws NumberFormatException, InvalidIndexException {
        int index = Integer.parseInt(input.substring(command.length()).trim()) - 1;
        if (index < 1 || index > tasks.getTotal()) {
            throw new InvalidIndexException();
        }
        return index;
    }
}
