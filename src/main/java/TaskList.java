import task.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();

    public void printList() {
        System.out.println("\tHere are the tasks in your list:");
        int numOfTasks = tasks.size();
        for (int i = 0; i < numOfTasks; i++) {
            Task task = tasks.get(i);
            System.out.println("\t" + (i + 1) + ". " + task.toString());
        }
    }

    public int getTotal() {
        return tasks.size();
    }

    public void markTaskAsDone(int index, boolean print) {
        Task task = tasks.get(index);
        task.markAsDone();
        if (print) {
            System.out.println("\tNice! I've marked this task as done: ");
            System.out.println("\t" + task.toString());
        }
    }

    public void removeTask(int index, boolean print) {
        Task task = tasks.get(index);
        tasks.remove(index);
        if (print) {
            System.out.println("\tNoted. I've removed this task: ");
            printTaskAndTotal(task);
        }
    }

    public void addToTaskList(Task task, boolean print) {
        tasks.add(task);
        if (print) {
            System.out.println("\tGot it. I've added this task:");
            printTaskAndTotal(task);
        }
    }

    public void printTaskAndTotal(Task task) {
        System.out.println("\t\t" + task.toString());
        int total = tasks.size();
        if (total == 1) {
            System.out.println("\tNow you only have 1 task in the list!");
        } else {
            System.out.println("\tNow you have " + total + " tasks in the list.");
        }
    }
}
