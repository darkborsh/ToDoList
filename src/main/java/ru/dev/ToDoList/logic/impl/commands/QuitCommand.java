package ru.dev.ToDoList.logic.impl.commands;

import ru.dev.ToDoList.logic.TaskDao;
import ru.dev.ToDoList.logic.impl.Command;
import ru.dev.ToDoList.model.CommandFormat;
import ru.dev.ToDoList.model.Task;

import java.util.Optional;
import java.util.stream.Stream;

public class QuitCommand implements Command {
    public static final String NAME = "quit";

    @Override
    public Optional<String> validate(CommandFormat cmdFormat) {
        String args = cmdFormat.getArgs();
        if (args != null) {
            return Optional.of("Команда quit не должна содержать аргументов");
        }
        return Optional.empty();
    }

    @Override
    public Stream<Task> apply(CommandFormat cmdFormat, TaskDao taskDao) {
        System.exit(0);
        return Stream.empty();
    }
}
