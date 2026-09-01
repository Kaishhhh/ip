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
        int taskCount = 0;

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                System.out.println("____________________________________________________________");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + "." + tasks[i]);
                }
                System.out.println("____________________________________________________________");
            } else if (input.startsWith("mark ")) {
                int index = Integer.parseInt(input.substring(5)) - 1;
                tasks[index].markAsDone();
                System.out.println("____________________________________________________________");
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  " + tasks[index]);
                System.out.println("____________________________________________________________");
            } else if (input.startsWith("unmark ")) {
                int index = Integer.parseInt(input.substring(7)) - 1;
                tasks[index].markAsNotDone();
                System.out.println("____________________________________________________________");
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("  " + tasks[index]);
                System.out.println("____________________________________________________________");
            } else if (input.startsWith("todo ")) {
                String desc = input.substring(5);
                Task t = new Todo(desc);
                tasks[taskCount++] = t;
                printAdded(t, taskCount);
            } else if (input.startsWith("deadline ")) {
                String[] parts = input.substring(9).split(" /by ");
                Task t = new Deadline(parts[0], parts[1]);
                tasks[taskCount++] = t;
                printAdded(t, taskCount);
            } else if (input.startsWith("event ")) {
                String[] parts = input.substring(6).split(" /from ");
                String desc = parts[0];
                String[] timeParts = parts[1].split(" /to ");
                Task t = new Event(desc, timeParts[0], timeParts[1]);
                tasks[taskCount++] = t;
                printAdded(t, taskCount);
            } else {
                System.out.println("____________________________________________________________");
                System.out.println("I'm sorry, I don't understand that command.");
                System.out.println("____________________________________________________________");
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints the confirmation message after a task has been added.
     *
     * @param task The task that was added.
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
