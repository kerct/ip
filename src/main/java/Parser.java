import exception.EmptyNameException;
import exception.InvalidIndexException;
import exception.MissingArgumentException;
import task.Deadline;
import task.Event;

/**
 * Acts an a helper class to make sense of user inputs.
 */
public class Parser {
    /**
     * Returns the Command to carry out based on the given input.
     *
     * @param input The input keyed in by the user.
     * @return Command to be executed.
     */
    public Command identifyCommand(String input) {
        if (input.trim().equalsIgnoreCase(Command.LIST.getKeyword())) {
            return Command.LIST;
        } else if (input.startsWith(Command.DONE.getKeyword())) {
            return Command.DONE;
        } else if (input.startsWith(Command.DELETE.getKeyword())) {
            return Command.DELETE;
        } else if (input.startsWith(Command.TODO.getKeyword())) {
            return Command.TODO;
        } else if (input.startsWith(Command.DEADLINE.getKeyword())) {
            return Command.DEADLINE;
        } else if (input.startsWith(Command.EVENT.getKeyword())) {
            return Command.EVENT;
        } else if (input.startsWith(Command.FIND.getKeyword())) {
            return Command.FIND;
        } else if (input.trim().equalsIgnoreCase(Command.EXIT.getKeyword())) {
            return Command.EXIT;
        } else {
            return Command.INVALID;
        }
    }

    /**
     * Returns the index of the task list which the user is referring to,
     * based on the given input and recognised command.
     *
     * @param input The input keyed in by the user.
     * @param command The identified command.
     * @param numOfTasks The number of tasks in the task list.
     * @return The index of the identified task in the task list, which starts from 0.
     * @throws NumberFormatException If the given input is not a number.
     * @throws InvalidIndexException If the given input is a number that is out of range.
     */
    public int getIndexFromInput(String input, Command command, int numOfTasks)
            throws NumberFormatException, InvalidIndexException {
        int index = Integer.parseInt(input.substring(command.getKeyword().length()).trim()) - 1;
        if (index < 0 || index > numOfTasks - 1) {
            throw new InvalidIndexException();
        }
        return index;
    }

    /**
     * Returns the name of the task obtained from the given input.
     *
     * @param input The input keyed in by the user.
     * @param command The identified command.
     * @return The name of the task inputted.
     * @throws EmptyNameException If there was no name inputted.
     */
    public String getNameFromInput(String input, Command command) throws EmptyNameException {
        String name = input.substring(command.getKeyword().length()).trim();
        if (name.length() == 0) {
            throw new EmptyNameException();
        }
        return name;
    }

    /**
     * Creates and returns a Deadline object from the given input.
     *
     * @param input The input keyed in by the user.
     * @return The new Deadline object.
     * @throws MissingArgumentException If there was no deadline specified.
     */
    public Deadline getDeadlineFromInput(String input) throws MissingArgumentException {
        int byIndex = input.indexOf(Command.BY.getKeyword());
        if (byIndex == -1) {
            throw new MissingArgumentException();
        }
        String name = input.substring(Command.DEADLINE.getKeyword().length(), byIndex).trim();
        String by = input.substring(byIndex + Command.BY.getKeyword().length()).trim();
        return new Deadline(name, by);
    }

    /**
     * Creates and returns an Event object from the given input.
     *
     * @param input The input keyed in by the user.
     * @return The new Event object.
     * @throws MissingArgumentException If the event date or time was not specified.
     */
    public Event getEventFromInput(String input) throws MissingArgumentException {
        int atIndex = input.indexOf(Command.AT.getKeyword());
        if (atIndex == -1) {
            throw new MissingArgumentException();
        }
        String name = input.substring(Command.EVENT.getKeyword().length(), atIndex).trim();
        String at = input.substring(atIndex + Command.AT.getKeyword().length()).trim();
        return new Event(name, at);
    }
}
