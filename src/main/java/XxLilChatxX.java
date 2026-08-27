import java.util.Scanner;

public class XxLilChatxX {
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

        String[] tasks = new String[100];
        boolean[] isDone = new boolean[100];
        int taskCount = 0;

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                System.out.println("____________________________________________________________");
                for (int i = 0; i < taskCount; i++) {
                    String status = isDone[i] ? "X" : " ";
                    System.out.println((i + 1) + ".[" + status + "] " + tasks[i]);
                }
                System.out.println("____________________________________________________________");
            } else if (input.startsWith("mark ")) {
                int index = Integer.parseInt(input.substring(5)) - 1;
                isDone[index] = true;
                System.out.println("____________________________________________________________");
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  [X] " + tasks[index]);
                System.out.println("____________________________________________________________");
            } else if (input.startsWith("unmark ")) {
            int index = Integer.parseInt(input.substring(7)) - 1;
            isDone[index] = false;
            System.out.println("____________________________________________________________");
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("  [ ] " + tasks[index]);
            System.out.println("____________________________________________________________");
        } else {
                tasks[taskCount] = input;
                taskCount++;
                System.out.println("____________________________________________________________");
                System.out.println("added: " + input);
                System.out.println("____________________________________________________________");
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}
