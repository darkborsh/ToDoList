package ru.dev.ToDoList.logic.impl.commands;

import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.dev.ToDoList.logic.TaskDao;
import ru.dev.ToDoList.presenters.ErrorHandler;
import ru.dev.ToDoList.model.CommandFormat;

import java.util.Map;
import java.util.function.*;

@Component
public class CommandConsumer implements Consumer<CommandFormat> {
    private final Map<String, Pair<Predicate<CommandFormat>, BiConsumer<CommandFormat, TaskDao>>> commandFactory;
    private final ErrorHandler errorHandler;
    private final TaskDao taskDao;

    @Autowired
    public CommandConsumer(Map<String, Pair<Predicate<CommandFormat>, BiConsumer<CommandFormat, TaskDao>>> commandFactory, ErrorHandler errorHandler,
                           TaskDao taskDao) {
        this.commandFactory = commandFactory;
        this.errorHandler = errorHandler;
        this.taskDao = taskDao;
    }

    @Override
    public void accept(CommandFormat commandFormat) {
        Pair<Predicate<CommandFormat>, BiConsumer<CommandFormat, TaskDao>> consumer = null;
        if (commandFormat != null) {
            consumer = commandFactory.get(commandFormat.getName());
        }
        if (consumer != null) {
            if (consumer.getKey().test(commandFormat)) {
                consumer.getValue().accept(commandFormat, taskDao);
            }
        } else {
            errorHandler.handle("Неизвестная команда");
        }
    }
}
