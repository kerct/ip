import task.Task;

import java.util.ArrayList;

/**
 * Represents the list of Tasks in a Duke program.
 */
public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Prints out all the tasks in the current list.
     */
    public void printList() {
        System.out.println("\tHere are the tasks in your list:");
        int numOfTasks = tasks.size();
        for (int i = 0; i < numOfTasks; i++) {
            Task task = tasks.get(i);
            System.out.println("\t" + (i + 1) + ". " + task.toString());
        }
    }

    /**
     * Returns the total number of tasks in the list.
     *
     * @return Size of the task list.
     */
    public int getTotal() {
        return tasks.size();
    }

    /**
     * Marks the task at the given index as completed.
     *
     * @param index Index of the task to be marked as done.
     * @return The task marked as done.
     */
    public Task markTaskAsDone(int index) {
        Task task = tasks.get(index);
        task.markAsDone();
        return task;
    }

    /**
     * Removes the task at the given index from the list.
     *
     * @param index Index of the task to be removed.
     * @return The task which was removed from the list.
     */
    public Task removeTask(int index) {
        Task task = tasks.get(index);
        tasks.remove(index);
        return task;
    }

    /**
     * Adds the given Task to the list.
     *
     * @param task Task to be added to the list.
     */
    public void addToTaskList(Task task) {
        tasks.add(task);
    }
}
