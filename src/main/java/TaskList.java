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

    public Task markTaskAsDone(int index) {
        Task task = tasks.get(index);
        task.markAsDone();
        return task;
    }

    public Task removeTask(int index) {
        Task task = tasks.get(index);
        tasks.remove(index);
        return task;
    }

    public void addToTaskList(Task task) {
        tasks.add(task);
    }
}
