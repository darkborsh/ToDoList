package ru.dev.ToDoList.logic.impl.commands;

import ru.dev.ToDoList.logic.TaskDao;
import ru.dev.ToDoList.logic.impl.Command;
import ru.dev.ToDoList.model.CommandFormat;
import ru.dev.ToDoList.model.Task;

import java.util.Optional;
import java.util.stream.Stream;

public class PrintCommand implements Command {
    public static final String NAME = "print";

    @Override
    public Optional<String> validate(CommandFormat cmdFormat) {
        String args = cmdFormat.getArgs();
        if (!allPrinted(args) || !isEmpty(args)) {
            return Optional.of("Неверный формат команды print");
        }
        return Optional.empty();
    }

    @Override
    public Stream<Task> apply(CommandFormat cmdFormat, TaskDao taskDao) {
        return taskDao.find(null, allPrinted(cmdFormat.getArgs()));
    }

    private static boolean allPrinted(String args) {
        return args != null && args.equals("all");
    }

    private static boolean isEmpty(String args) {
        return args == null || args.equals("");
    }
}
