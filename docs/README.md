# XxLilChatxX User Guide

XxLilChatxX is a desktop chatbot app for managing your tasks, optimized for use via a Command Line Interface (CLI). If you can type fast, XxLilChatxX can help you track your tasks faster than a traditional GUI app.

## Quick start

1. Ensure you have Java 25 installed on your computer.
2. Download the latest `.jar` file from [here](https://github.com/Kaishhhh/ip/releases).
3. Copy the file to the folder you want to use as the home folder for XxLilChatxX.
4. Open a terminal, `cd` into that folder, and run:

java -jar XxLilChatxX.jar

5. Type a command and press Enter. Some example commands:
    - `todo read book` : Adds a todo task.
    - `list` : Shows all your tasks.
    - `bye` : Exits the app.

## Features

> **Notes about the command format:**
> - Words in `UPPER_CASE` are parameters supplied by you. E.g. in `todo DESCRIPTION`, `DESCRIPTION` should be replaced, e.g. `todo read book`.
> - `INDEX` refers to the task number shown in the `list` output, starting from 1.

### Adding a todo: `todo`
Adds a task with no date or time attached.

Format: `todo DESCRIPTION`

Example: `todo read book`

Got it. I've added this task:
[T][ ] read book
Now you have 1 tasks in the list.


### Adding a deadline: `deadline`
Adds a task that needs to be done by a specific date/time.

Format: `deadline DESCRIPTION /by DATE`

Example: `deadline return book /by Sunday`

Got it. I've added this task:
[D][ ] return book (by: Sunday)
Now you have 2 tasks in the list.


### Adding an event: `event`
Adds a task that starts and ends at specific times.

Format: `event DESCRIPTION /from START /to END`

Example: `event project meeting /from Mon 2pm /to 4pm`

Got it. I've added this task:
[E][ ] project meeting (from: Mon 2pm to: 4pm)
Now you have 3 tasks in the list.


### Listing all tasks: `list`
Shows all tasks currently in your list.

Format: `list`

### Marking a task as done: `mark`
Marks the specified task as done.

Format: `mark INDEX`

Example: `mark 2` marks the 2nd task in the list as done.

### Unmarking a task: `unmark`
Marks the specified task as not done.

Format: `unmark INDEX`

Example: `unmark 2` marks the 2nd task in the list as not done.

### Deleting a task: `delete`
Removes the specified task from the list.

Format: `delete INDEX`

Example: `delete 3` removes the 3rd task in the list.

### Finding tasks: `find`
Finds tasks whose description contains the given keyword.

Format: `find KEYWORD`

The search is case-sensitive and matches any part of the description.

Example: `find book`

Here are the matching tasks in your list:
1.[T][ ] read book
2.[D][ ] return book (by: Sunday)


### Exiting the program: `bye`
Exits XxLilChatxX.

Format: `bye`

## Saving the data
XxLilChatxX automatically saves your tasks to disk after every command that changes the task list (add, delete, mark, unmark). You don't need to save manually.

## Editing the data file
XxLilChatxX data is saved automatically at `[JAR file location]/data/xxlilchatxx.txt`. Advanced users may edit this file directly.

> ⚠️ **Caution:** If your edits make the data file invalid, XxLilChatxX will skip corrupted lines when loading. We recommend backing up the file before editing it directly.

## FAQ

**Q: How do I transfer my data to another computer?**
A: Install XxLilChatxX on the other computer, then copy over your `data/xxlilchatxx.txt` file into the same relative location.

## Command summary

| Action | Format | Example |
|---|---|---|
| Todo | `todo DESCRIPTION` | `todo read book` |
| Deadline | `deadline DESCRIPTION /by DATE` | `deadline return book /by Sunday` |
| Event | `event DESCRIPTION /from START /to END` | `event meeting /from Mon 2pm /to 4pm` |
| List | `list` | `list` |
| Mark | `mark INDEX` | `mark 2` |
| Unmark | `unmark INDEX` | `unmark 2` |
| Delete | `delete INDEX` | `delete 3` |
| Find | `find KEYWORD` | `find book` |
| Exit | `bye` | `bye` |