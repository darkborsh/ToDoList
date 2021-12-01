package ru.dev.ToDoList.logic.impl.commands;

import org.springframework.stereotype.Component;
import ru.dev.ToDoList.logic.TaskDao;
import ru.dev.ToDoList.logic.Command;
import ru.dev.ToDoList.model.CommandFormat;
import ru.dev.ToDoList.model.Task;

import java.util.Optional;
import java.util.stream.Stream;

@Component
public class PrintCommand implements Command {
    private static final String NAME = "print";

    @Override
    public Optional<String> validate(CommandFormat cmdFormat) {
        String args = cmdFormat.getArgs();
        if (!isEmpty(args) && !allPrinted(args)) {
            return Optional.of("Неверный формат команды print");
        }
        return Optional.empty();
    }

    @Override
    public Stream<Task> apply(CommandFormat cmdFormat, TaskDao taskDao) {
        return taskDao.find(null, !allPrinted(cmdFormat.getArgs()));
    }

    private boolean allPrinted(String args) {
        return args != null && args.equals("all");
    }

    private boolean isEmpty(String args) {
        return args == null || args.equals("");
    }

    @Override
    public String getName() {
        return NAME;
    }
}
