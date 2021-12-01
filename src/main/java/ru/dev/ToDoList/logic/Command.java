package ru.dev.ToDoList.logic;

import ru.dev.ToDoList.model.CommandFormat;
import ru.dev.ToDoList.model.Task;

import java.util.Optional;
import java.util.stream.Stream;

public interface Command {
    Optional<String> validate(CommandFormat cmdFormat);
    Stream<Task> apply(CommandFormat cmdFormat, TaskDao taskDao);
    String getName();
}
