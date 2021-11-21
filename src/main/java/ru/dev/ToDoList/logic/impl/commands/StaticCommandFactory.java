package ru.dev.ToDoList.logic.impl.commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ru.dev.ToDoList.logic.TaskDao;
import ru.dev.ToDoList.model.CommandFormat;

import javafx.util.Pair;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Predicate;

@Component
public class StaticCommandFactory implements Function<String, Pair<Predicate<CommandFormat>, BiConsumer<CommandFormat, TaskDao>>> {
    private final Map<String, Pair<Predicate<CommandFormat>, BiConsumer<CommandFormat, TaskDao>>> commands;

    @Autowired
    public StaticCommandFactory(Map<String, Pair<Predicate<CommandFormat>, BiConsumer<CommandFormat, TaskDao>>> commands) {
        this.commands = commands;
    }

    @Override
    public Pair<Predicate<CommandFormat>, BiConsumer<CommandFormat, TaskDao>> apply(String commandName) {
        return commands.get(commandName);
    }
}
