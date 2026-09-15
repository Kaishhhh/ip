package xxlilchatxx;

import java.util.Scanner;

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
        String banner =
                "██╗  ██╗██╗  ██╗██╗     ██╗██╗      ██████╗██╗  ██╗ █████╗ ████████╗██╗  ██╗██╗  ██╗\n"
                        + "╚██╗██╔╝╚██╗██╔╝██║     ██║██║     ██╔════╝██║  ██║██╔══██╗╚══██╔══╝╚██╗██╔╝╚██╗██╔╝\n"
                        + " ╚███╔╝  ╚███╔╝ ██║     ██║██║     ██║     ███████║███████║   ██║    ╚███╔╝  ╚███╔╝ \n"
                        + " ██╔██╗  ██╔██╗ ██║     ██║██║     ██║     ██╔══██║██╔══██║   ██║    ██╔██╗  ██╔██╗ \n"
                        + "██╔╝ ██╗██╔╝ ██╗███████╗██║███████╗╚██████╗██║  ██║██║  ██║   ██║   ██╔╝ ██╗██╔╝ ██╗\n"
                        + "╚═╝  ╚═╝╚═╝  ╚═╝╚══════╝╚═╝╚══════╝ ╚═════╝╚═╝  ╚═╝╚═╝  ╚═╝   ╚═╝   ╚═╝  ╚═╝╚═╝  ╚═╝\n";
        System.out.println("____________________________________________________________");
        System.out.println(banner);
        System.out.println("Hello! I'm XxLilChatxX.");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        Task[] tasks = new Task[100];
        int taskCount = Storage.load(tasks);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                break;
            }

            try {
                if (input.equals("list")) {
                    System.out.println("____________________________________________________________");
                    for (int i = 0; i < taskCount; i++) {
                        System.out.println((i + 1) + "." + tasks[i]);
                    }
                    System.out.println("____________________________________________________________");
                } else if (input.startsWith("mark ")) {
                    int index = Integer.parseInt(input.substring(5)) - 1;
                    if (index < 0 || index >= taskCount) {
                        throw new XxLilChatxXException("OOPS!!! That task number doesn't exist.");
                    }
                    tasks[index].markAsDone();
                    Storage.save(tasks, taskCount);
                    System.out.println("____________________________________________________________");
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  " + tasks[index]);
                    System.out.println("____________________________________________________________");
                } else if (input.startsWith("unmark ")) {
                    int index = Integer.parseInt(input.substring(7)) - 1;
                    if (index < 0 || index >= taskCount) {
                        throw new XxLilChatxXException("OOPS!!! That task number doesn't exist.");
                    }
                    tasks[index].markAsNotDone();
                    Storage.save(tasks, taskCount);
                    System.out.println("____________________________________________________________");
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("  " + tasks[index]);
                    System.out.println("____________________________________________________________");
                } else if (input.equals("todo") || input.startsWith("todo ")) {
                    String desc = input.length() > 4 ? input.substring(5).trim() : "";
                    if (desc.isEmpty()) {
                        throw new XxLilChatxXException("OOPS!!! The description of a todo cannot be empty.");
                    }
                    Task t = new Todo(desc);
                    tasks[taskCount++] = t;
                    Storage.save(tasks, taskCount);
                    printAdded(t, taskCount);
                } else if (input.equals("deadline") || input.startsWith("deadline ")) {
                    String rest = input.length() > 8 ? input.substring(9).trim() : "";
                    String[] parts = rest.split(" /by ");
                    if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
                        throw new XxLilChatxXException(
                                "OOPS!!! A deadline needs a description and a /by date, e.g. deadline return book /by Sunday");
                    }
                    Task t = new Deadline(parts[0], parts[1]);
                    tasks[taskCount++] = t;
                    Storage.save(tasks, taskCount);
                    printAdded(t, taskCount);
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
                    tasks[taskCount++] = t;
                    Storage.save(tasks, taskCount);
                    printAdded(t, taskCount);
                } else {
                    throw new XxLilChatxXException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (XxLilChatxXException e) {
                System.out.println("____________________________________________________________");
                System.out.println(e.getMessage());
                System.out.println("____________________________________________________________");
            } catch (NumberFormatException e) {
                System.out.println("____________________________________________________________");
                System.out.println("OOPS!!! Please provide a valid task number.");
                System.out.println("____________________________________________________________");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("____________________________________________________________");
                System.out.println("OOPS!!! That task number doesn't exist.");
                System.out.println("____________________________________________________________");
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints the confirmation message after a task has been added.
     *
     * @param task  The task that was added.
     * @param count The total number of tasks after adding.
     */
    private static void printAdded(Task task, int count) {
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + count + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }
}
