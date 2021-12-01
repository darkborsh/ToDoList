package ru.dev.ToDoList.logic;

import org.springframework.stereotype.Component;
import ru.dev.ToDoList.logic.TaskDao;
import ru.dev.ToDoList.logic.Command;
import ru.dev.ToDoList.model.Task;
import ru.dev.ToDoList.presenters.ErrorHandler;
import ru.dev.ToDoList.model.CommandFormat;
import ru.dev.ToDoList.presenters.TaskPrinter;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class CommandConsumer implements Consumer<CommandFormat> {
    private final Map<String, Command> commands;
    private final ErrorHandler errorHandler;
    private final TaskDao taskDao;
    private final TaskPrinter taskPrinter;

    public CommandConsumer(List<Command> commands, ErrorHandler errorHandler,
                           TaskDao taskDao, TaskPrinter taskPrinter) {
        this.commands = commands.stream().collect(Collectors.toMap(Command::getName, Function.identity()));
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
