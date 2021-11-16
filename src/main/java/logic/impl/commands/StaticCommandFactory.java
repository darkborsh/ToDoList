package logic.impl.commands;

import javafx.util.Pair;
import logic.ErrorHandler;
import logic.TaskDao;
import logic.TaskPrinter;
import logic.impl.TaskPrinterImpl;
import logic.impl.commands.executors.AddExecutor;
import logic.impl.commands.validators.AddValidator;
import parser.CommandFormat;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class StaticCommandFactory implements Supplier<Map<String,
        Pair<Predicate<CommandFormat>, BiConsumer<CommandFormat, TaskDao>>>> {
    private final Map<String, Pair<Predicate<CommandFormat>, BiConsumer<CommandFormat, TaskDao>>> commands;

    public StaticCommandFactory(final ErrorHandler errorHandler) {
        final Map<String, Pair<Predicate<CommandFormat>, BiConsumer<CommandFormat, TaskDao>>> map = new HashMap<>();
        final TaskPrinter taskPrinter = new TaskPrinterImpl();

        Predicate<CommandFormat> validator = new AddValidator(errorHandler);
        BiConsumer<CommandFormat, TaskDao> executor = new AddExecutor();
        map.put(CommandNames.COMMAND_ADD, new Pair<>(validator, executor));

        this.commands = Collections.unmodifiableMap(map);
    }

    @Override
    public Map<String, Pair<Predicate<CommandFormat>, BiConsumer<CommandFormat, TaskDao>>> get() {
        return commands;
    }
}
