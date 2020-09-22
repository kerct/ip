import exception.InvalidIndexException;

public class Parser {
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
        } else if (input.trim().equalsIgnoreCase(Command.EXIT.getKeyword())) {
            return Command.EXIT;
        } else {
            return Command.INVALID;
        }
    }

    public int getIndexFromInput(String input, String command, int numOfTasks)
            throws NumberFormatException, InvalidIndexException {
        int index = Integer.parseInt(input.substring(command.length()).trim()) - 1;
        if (index < 0 || index > numOfTasks) {
            throw new InvalidIndexException();
        }
        return index;
    }
}
