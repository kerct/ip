import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String line = "\t-------------------------------------";
        System.out.println(logo);
        System.out.println(line);
        System.out.println("\tHello! I'm Duke");
        System.out.println("\tWhat can I do for you?");
        System.out.println(line);

        Scanner in = new Scanner(System.in);
        String input = in.nextLine();

        while(!input.equalsIgnoreCase("bye")) {
            System.out.println(line);
            System.out.println("\t" + input);
            System.out.println(line);
            input = in.nextLine();
        }

        System.out.println(line);
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println(line);
    }
}
