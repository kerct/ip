import java.io.FileNotFoundException;
import java.util.Scanner;

public class Duke {
    private final String EXIT_COMMAND = "bye";

    private Ui ui;
    private Parser parser;

    public Duke(String filePath) {
        ui = new Ui();
        Storage storage = new Storage(filePath);
        TaskList tasks = new TaskList();
        parser = new Parser(storage, tasks, ui);
        try {
            storage.load(parser);
        } catch (FileNotFoundException e) {
            ui.printErrorMessage(e.getMessage());
        }
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (!input.trim().equalsIgnoreCase(EXIT_COMMAND)) {
            parser.handleInput(input, true);
            input = scanner.nextLine();
        }
        ui.printEndMessage();
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
