# User Guide
Duke is a desktop app for managing tasks, 
optimised for use via a Command Line Interface (CLI). 

These are the type of tasks that can be managed in Duke:
1. Todo
1. Deadline
1. Event

## Features 
>Note about the command format:
>* Words in `UPPER_CASE` are the parameters to be supplied by the user.

### Adding a Todo: `todo`
Adds a Todo to the list of tasks.

Format: `todo TASK_NAME`

Example: `todo CS iP Level 10`

### Adding a Deadline: `deadline`
Adds a Deadline to the list of tasks.

Format: `deadline TASK_NAME /by TASK_DEADLINE`

Example: `deadline User Guide /by 1st Oct`

### Adding an Event: `event`
Adds an Event to the list of tasks.

Format: `event TASK_NAME /at TASK_DAY_OR_TIME`

Example: `event CS Tutorial /at Friday 12pm`

### Listing all tasks: `list`
Shows a list of all the tasks in Duke.

Format: `list`
* The `list` command is case-insensitive.

Example: `list` displays the list of tasks currently stored in Duke.

### Marking a task as done: `done`
Marks a certain task in the list as done.

Format: `done TASK_INDEX`
* The `TASK_INDEX` starts from 1.

Example: `done 2` marks the second task in the list as done
if there are 2 or more tasks in the list, 
or is recognised as an invalid command
if there are less than 2 tasks in the list.

### Deleting a task: `delete`
Removes a certain task from the list.

Format: `delete TASK_INDEX`
* The `TASK_INDEX` starts from 1.

Example: `delete 2` deletes the second task in the list 
if there are 2 or more tasks in the list, 
or is recognised as an invalid command
if there are less than 2 tasks in the list.

### Saving data
The tasks that you add or mark as done will be saved externally in a file.
When you start the program, these data will be loaded 
so that you can continue from where you left off the previous time. 

### Finding a task: `find`
Finds a task by searching for a keyword.

Format: `find KEYWORD`

Example: `find CS` displays a list of tasks whose names contain `CS`.

### Exiting the program: `bye`
Quits the Duke program.

Format: `bye`
* The `bye` command is case-insensitive.

Example: `bye` causes the program to end, 
and the user is no longer prompted for more inputs.