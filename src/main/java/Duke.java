import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String dottedLine = "\t-------------------------------------";
        System.out.println(logo);
        System.out.println(dottedLine);
        System.out.println("\tHello! I'm Duke");
        System.out.println("\tWhat can I do for you?");
        System.out.println(dottedLine);

        Scanner in = new Scanner(System.in);
        String[] list = new String[100];
        int numOfTasks = 0;
        String input = in.nextLine();

        while (!input.equalsIgnoreCase("bye")) {
            System.out.println(dottedLine);
            if (input.equals("list")) {
                printList(list, numOfTasks);
            }
            else {
                list[numOfTasks] = input;
                numOfTasks++;
                System.out.println("\tadded: " + input);
            }
            System.out.println(dottedLine);
            input = in.nextLine();
        }

        System.out.println(dottedLine);
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println(dottedLine);
    }

    private static void printList(String[] list, int numOfTasks) {
        for (int i = 0; i < numOfTasks; i++) {
            System.out.println("\t" + (i + 1) + ". " + list[i]);
        }
    }
}
