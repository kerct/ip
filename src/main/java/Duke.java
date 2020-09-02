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
        Task[] tasks = new Task[100];
        int numOfTasks = 0;
        String input = in.nextLine();

        while (!input.equalsIgnoreCase("bye")) {
            System.out.println(dottedLine);
            if (input.equalsIgnoreCase("list")) {
                printList(tasks, numOfTasks);
            }
            else if (input.startsWith("done")) {
                int index = Integer.parseInt(input.substring(4).trim());
                tasks[index - 1].markAsDone();
                System.out.println("\tNice! I've marked this task as done: ");
                System.out.println("\t" + tasks[index - 1].toString());
            }
            else if (input.startsWith("todo")) {
                String name = input.substring(5).trim();
                Task todo = new Todo(name);
                tasks[numOfTasks] = todo;
                numOfTasks++;
                System.out.println("\tGot it. I've added this task:");
                System.out.println("\t\t" + todo.toString());
                System.out.println("\tNow you have " + numOfTasks + " tasks in the list.");
            }
            else if (input.startsWith("deadline")) {
                int byIndex = input.indexOf("/by");
                String name = input.substring(8, byIndex).trim();
                String by = input.substring(byIndex + 3).trim();
                Task deadline = new Deadline(name, by);
                tasks[numOfTasks] = deadline;
                numOfTasks++;
                System.out.println("\tGot it. I've added this task:");
                System.out.println("\t\t" + deadline.toString());
                System.out.println("\tNow you have " + numOfTasks + " tasks in the list.");
            }
            else if (input.startsWith("event")) {
                int atIndex = input.indexOf("/at");
                String name = input.substring(5, atIndex).trim();
                String at = input.substring(atIndex + 3).trim();
                Task event = new Event(name, at);
                tasks[numOfTasks] = event;
                numOfTasks++;
                System.out.println("\tGot it. I've added this task:");
                System.out.println("\t\t" + event.toString());
                System.out.println("\tNow you have " + numOfTasks + " tasks in the list.");
            }
            else {
                Task task = new Task(input);
                tasks[numOfTasks] = task;
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

    private static void printList(Task[] tasks, int numOfTasks) {
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < numOfTasks; i++) {
            Task task = tasks[i];
            System.out.println("\t" + (i + 1) + ". " + task.toString());
        }
    }
}
