package ru.dev.ToDoList.logic.impl.commands;

import org.springframework.stereotype.Component;
import ru.dev.ToDoList.logic.TaskDao;
import ru.dev.ToDoList.logic.impl.Command;
import ru.dev.ToDoList.model.Task;
import ru.dev.ToDoList.presenters.ErrorHandler;
import ru.dev.ToDoList.model.CommandFormat;
import ru.dev.ToDoList.presenters.TaskPrinter;

import java.util.Map;
import java.util.Optional;
import java.util.function.*;
import java.util.stream.Stream;

@Component
public class CommandConsumer implements Consumer<CommandFormat> {
    private final Map<String, Command> commands;
    private final ErrorHandler errorHandler;
    private final TaskDao taskDao;
    private final TaskPrinter taskPrinter;

    public CommandConsumer(Map<String, Command> commands, ErrorHandler errorHandler,
                           TaskDao taskDao, TaskPrinter taskPrinter) {
        this.commands = commands;
        this.errorHandler = errorHandler;
        this.taskDao = taskDao;
        this.taskPrinter = taskPrinter;
    }

    @Override
    public void accept(CommandFormat commandFormat) {
        Command command = null;
        if (commandFormat != null) {
            command = commands.get(commandFormat.getName());
        }
        if (command != null) {
            Optional<String> error = command.validate(commandFormat);
            if (error.isPresent()) {
                errorHandler.handle(error.get());
            } else {
                Stream<Task> tasks = command.apply(commandFormat, taskDao);
                taskPrinter.print(tasks);
            }
        } else {
            errorHandler.handle("Неизвестная команда");
        }
    }
}
