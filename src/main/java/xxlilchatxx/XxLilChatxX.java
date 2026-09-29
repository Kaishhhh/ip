package xxlilchatxx;

import java.util.ArrayList;

/**
 * Entry point for the XxLilChatxX chatbot application.
 * Handles user commands to add, list, mark, and unmark tasks.
 */
public class XxLilChatxX {

    /**
     * Runs the chatbot, reading commands from standard input until "bye" is entered.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        Ui ui = new Ui();
        ArrayList<Task> loaded = new ArrayList<>();
        Storage.load(loaded);
        TaskList tasks = new TaskList(loaded);

        ui.showWelcome();

        while (true) {
            String input = ui.readCommand();

            if (input.equals("bye")) {
                break;
            }

            try {
                if (input.equals("list")) {
                    ui.showLine();
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + "." + tasks.get(i));
                    }
                    ui.showLine();
                } else if (input.startsWith("mark ")) {
                    int index = Integer.parseInt(input.substring(5)) - 1;
                    if (index < 0 || index >= tasks.size()) {
                        throw new XxLilChatxXException("OOPS!!! That task number doesn't exist.");
                    }
                    tasks.get(index).markAsDone();
                    Storage.save(tasks.getTasks());
                    ui.showLine();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  " + tasks.get(index));
                    ui.showLine();
                } else if (input.startsWith("unmark ")) {
                    int index = Integer.parseInt(input.substring(7)) - 1;
                    if (index < 0 || index >= tasks.size()) {
                        throw new XxLilChatxXException("OOPS!!! That task number doesn't exist.");
                    }
                    tasks.get(index).markAsNotDone();
                    Storage.save(tasks.getTasks());
                    ui.showLine();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("  " + tasks.get(index));
                    ui.showLine();
                } else if (input.startsWith("delete ")) {
                    int index = Integer.parseInt(input.substring(7)) - 1;
                    if (index < 0 || index >= tasks.size()) {
                        throw new XxLilChatxXException("OOPS!!! That task number doesn't exist.");
                    }
                    Task removed = tasks.delete(index);
                    Storage.save(tasks.getTasks());
                    ui.showLine();
                    System.out.println("Noted. I've removed this task:");
                    System.out.println("  " + removed);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    ui.showLine();
                } else if (input.equals("todo") || input.startsWith("todo ")) {
                    String desc = input.length() > 4 ? input.substring(5).trim() : "";
                    if (desc.isEmpty()) {
                        throw new XxLilChatxXException("OOPS!!! The description of a todo cannot be empty.");
                    }
                    Task t = new Todo(desc);
                    tasks.add(t);
                    Storage.save(tasks.getTasks());
                    printAdded(ui, t, tasks.size());
                } else if (input.equals("deadline") || input.startsWith("deadline ")) {
                    String rest = input.length() > 8 ? input.substring(9).trim() : "";
                    String[] parts = rest.split(" /by ");
                    if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
                        throw new XxLilChatxXException(
                                "OOPS!!! A deadline needs a description and a /by date, e.g. deadline return book /by Sunday");
                    }
                    Task t = new Deadline(parts[0], parts[1]);
                    tasks.add(t);
                    Storage.save(tasks.getTasks());
                    printAdded(ui, t, tasks.size());
                } else if (input.equals("event") || input.startsWith("event ")) {
                    String rest = input.length() > 5 ? input.substring(6).trim() : "";
                    String[] parts = rest.split(" /from ");
                    if (parts.length < 2 || parts[0].trim().isEmpty()) {
                        throw new XxLilChatxXException(
                                "OOPS!!! An event needs a description, /from time, and /to time.");
                    }
                    String[] timeParts = parts[1].split(" /to ");
                    if (timeParts.length < 2) {
                        throw new XxLilChatxXException(
                                "OOPS!!! An event needs both a /from time and a /to time.");
                    }
                    Task t = new Event(parts[0].trim(), timeParts[0], timeParts[1]);
                    tasks.add(t);
                    Storage.save(tasks.getTasks());
                    printAdded(ui, t, tasks.size());
                } else {
                    throw new XxLilChatxXException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (XxLilChatxXException e) {
                ui.showLine();
                ui.showError(e.getMessage());
                ui.showLine();
            } catch (NumberFormatException e) {
                ui.showLine();
                ui.showError("OOPS!!! Please provide a valid task number.");
                ui.showLine();
            }
        }

        ui.showGoodbye();
    }

    /**
     * Prints the confirmation message after a task has been added.
     *
     * @param task  The task that was added.
     * @param count The total number of tasks after adding.
     */
    private static void printAdded(Ui ui, Task task, int count) {
        ui.showLine();
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + count + " tasks in the list.");
        ui.showLine();
    }
}
