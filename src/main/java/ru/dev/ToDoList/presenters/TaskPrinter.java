package ru.dev.ToDoList.presenters;

import ru.dev.ToDoList.model.Task;

import java.util.stream.Stream;

public interface TaskPrinter {
    void print(Stream<Task> tasks);
}
