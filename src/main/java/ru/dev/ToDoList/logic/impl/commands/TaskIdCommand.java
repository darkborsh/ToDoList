package ru.dev.ToDoList.logic.impl.commands;

import ru.dev.ToDoList.logic.TaskDao;
import ru.dev.ToDoList.logic.impl.Command;
import ru.dev.ToDoList.model.CommandFormat;
import ru.dev.ToDoList.model.Task;

import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

public class TaskIdCommand implements Command {
    private final BiConsumer<CommandFormat, TaskDao> consumer;

    public TaskIdCommand(BiConsumer<CommandFormat, TaskDao> consumer) {
        this.consumer = consumer;
    }

    @Override
    public Optional<String> validate(CommandFormat cmdFormat) {
        if (cmdFormat.getId() == 0) {
            return Optional.of("Не указан идентификатор задачи");
        }
        return Optional.empty();
    }

    @Override
    public Stream<Task> apply(CommandFormat cmdFormat, TaskDao taskDao) {
        consumer.accept(cmdFormat, taskDao);
        return Stream.empty();
    }
}
