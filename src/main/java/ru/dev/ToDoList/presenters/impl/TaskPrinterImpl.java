package ru.dev.ToDoList.presenters.impl;

import org.springframework.stereotype.Component;
import ru.dev.ToDoList.presenters.TaskPrinter;
import ru.dev.ToDoList.model.Task;

import java.util.stream.Stream;

@Component
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
