package logic;

import model.Task;

import java.util.stream.Stream;

public class TaskPrinter {
    public void print(Stream<Task> tasks) {
        tasks.forEach(TaskPrinter::printTask);
    }

    private static void printTask(Task task) {
        System.out.printf("%s. [%s] %s\n",
                task.getId(),
                task.isCompleted() ? "x" : " ",
                task.getDescription());
    }
}