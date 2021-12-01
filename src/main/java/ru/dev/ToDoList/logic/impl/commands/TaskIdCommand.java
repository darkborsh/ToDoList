package ru.dev.ToDoList.logic.impl.commands;

import lombok.RequiredArgsConstructor;
import ru.dev.ToDoList.logic.TaskDao;
import ru.dev.ToDoList.logic.Command;
import ru.dev.ToDoList.model.CommandFormat;
import ru.dev.ToDoList.model.Task;

import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class TaskIdCommand implements Command {
    private final BiConsumer<CommandFormat, TaskDao> consumer;

    private final String NAME;

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

    @Override
    public String getName() {
        return NAME;
    }
}
