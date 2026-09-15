package xxlilchatxx;

import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * Handles saving tasks to and loading tasks from a file on disk.
 */
public class Storage {

    private static final String FILE_PATH = "./data/xxlilchatxx.txt";

    /**
     * Saves the given tasks to the data file, overwriting any existing content.
     *
     * @param tasks Array of tasks to save.
     * @param taskCount Number of tasks currently stored.
     */
    public static void save(Task[] tasks, int taskCount) {
        try {
            File file = new File(FILE_PATH);
            file.getParentFile().mkdirs();
            FileWriter writer = new FileWriter(file);
            for (int i = 0; i < taskCount; i++) {
                writer.write(tasks[i].toFileFormat() + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Warning: could not save tasks to file.");
        }
    }

    /**
     * Loads tasks from the data file into the given array.
     * If the file or its containing folder does not exist, no tasks are loaded.
     *
     * @param tasks Array to populate with loaded tasks.
     * @return Number of tasks successfully loaded.
     */
    public static int load(Task[] tasks) {
        int count = 0;
        File file = new File(FILE_PATH);

        if (!file.exists()) {
            return 0;
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = parseLine(line);
                if (task != null) {
                    tasks[count] = task;
                    count++;
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Warning: could not load tasks from file.");
        }

        return count;
    }

    /**
     * Parses a single line from the data file into a Task object.
     * Returns null if the line is corrupted or in an unexpected format.
     *
     * @param line A line from the data file.
     * @return The parsed Task, or null if parsing failed.
     */
    private static Task parseLine(String line) {
        try {
            String[] parts = line.split(" \\| ");
            String type = parts[0];
            boolean isDone = parts[1].equals("1");
            String description = parts[2];

            Task task;
            if (type.equals("T")) {
                task = new Todo(description);
            } else if (type.equals("D")) {
                task = new Deadline(description, parts[3]);
            } else if (type.equals("E")) {
                task = new Event(description, parts[3], parts[4]);
            } else {
                return null;
            }

            if (isDone) {
                task.markAsDone();
            }
            return task;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Warning: skipping corrupted line in data file: " + line);
            return null;
        }
    }
}