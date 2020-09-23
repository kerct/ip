import exception.InvalidIndexException;

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
    public int getIndexFromInput(String input, String command, int numOfTasks)
            throws NumberFormatException, InvalidIndexException {
        int index = Integer.parseInt(input.substring(command.length()).trim()) - 1;
        if (index < 0 || index > numOfTasks - 1) {
            throw new InvalidIndexException();
        }
        return index;
    }
}
