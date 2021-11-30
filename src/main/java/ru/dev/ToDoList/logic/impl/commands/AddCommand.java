package ru.dev.ToDoList.logic.impl.commands;

import ru.dev.ToDoList.logic.TaskDao;
import ru.dev.ToDoList.logic.impl.Command;
import ru.dev.ToDoList.model.CommandFormat;
import ru.dev.ToDoList.model.Task;

import java.util.Optional;
import java.util.stream.Stream;

public class AddCommand implements Command {
    public static final String NAME = "add";

    @Override
    public Optional<String> validate(CommandFormat cmdFormat) {
        String args = cmdFormat.getArgs();
        if (args == null || args.isEmpty()) {
            return Optional.of("Описание задачи не может быть пустым");
        }
        return Optional.empty();
    }

    @Override
    public Stream<Task> apply(CommandFormat cmdFormat, TaskDao taskDao) {
        taskDao.save(new Task(cmdFormat.getArgs()));
        return Stream.empty();
    }
}
