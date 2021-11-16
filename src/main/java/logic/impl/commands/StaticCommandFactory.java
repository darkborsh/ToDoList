package logic.impl.commands;

import javafx.util.Pair;
import presenters.ErrorHandler;
import logic.TaskDao;
import presenters.TaskPrinter;
import presenters.impl.TaskPrinterImpl;
import logic.impl.commands.executors.*;
import logic.impl.commands.validators.*;
import model.CommandFormat;

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

        Predicate<CommandFormat> validator = new TextValidator(errorHandler);
        BiConsumer<CommandFormat, TaskDao> executor = new AddExecutor();
        map.put(CommandNames.COMMAND_ADD, new Pair<>(validator, executor));

        executor = new SearchExecutor(taskPrinter);
        map.put(CommandNames.COMMAND_SEARCH, new Pair<>(validator, executor));

        validator = new PrintValidator(errorHandler);
        executor = new PrintExecutor(taskPrinter);
        map.put(CommandNames.COMMAND_PRINT, new Pair<>(validator, executor));

        validator = new IdValidator(errorHandler);
        executor = new ToggleExecutor();
        map.put(CommandNames.COMMAND_TOGGLE, new Pair<>(validator, executor));

        executor = new DeleteExecutor();
        map.put(CommandNames.COMMAND_DELETE, new Pair<>(validator, executor));

        validator = new EditValidator(errorHandler);
        executor = new EditExecutor();
        map.put(CommandNames.COMMAND_EDIT, new Pair<>(validator, executor));

        validator = new QuitValidator(errorHandler);
        executor = new QuitExecutor();
        map.put(CommandNames.COMMAND_QUIT, new Pair<>(validator, executor));

        this.commands = Collections.unmodifiableMap(map);
    }

    @Override
    public Map<String, Pair<Predicate<CommandFormat>, BiConsumer<CommandFormat, TaskDao>>> get() {
        return commands;
    }
}
