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
}
