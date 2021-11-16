package logic.impl;

import logic.TaskPrinter;
import model.Task;

import java.util.stream.Stream;

public class TaskPrinterImpl implements TaskPrinter {
    @Override
    public void print(Stream<Task> tasks) {
        tasks.forEach(TaskPrinterImpl::printTask);
    }

    private static void printTask(Task task) {
        System.out.printf("%d. [%s] %s\n",
                task.getId(),
                task.isCompleted() ? "x" : " ",
                task.getDescription());
    }
}
