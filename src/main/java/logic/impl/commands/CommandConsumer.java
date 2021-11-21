package logic.impl.commands;

import javafx.util.Pair;
import presenters.ErrorHandler;
import logic.TaskDao;
import model.CommandFormat;

import java.util.function.*;

public class CommandConsumer implements Consumer<CommandFormat> {
    private final Function<String, Pair<Predicate<CommandFormat>, BiConsumer<CommandFormat, TaskDao>>> commandFactory;
    private final ErrorHandler errorHandler;
    private final TaskDao taskDao;

    public CommandConsumer(Function<String,
            Pair<Predicate<CommandFormat>, BiConsumer<CommandFormat, TaskDao>>> commandFactory,
                           ErrorHandler errorHandler,
                           TaskDao taskDao) {
        this.commandFactory = commandFactory;
        this.errorHandler = errorHandler;
        this.taskDao = taskDao;
    }

    @Override
    public void accept(CommandFormat commandFormat) {
        Pair<Predicate<CommandFormat>, BiConsumer<CommandFormat, TaskDao>> consumer = null;
        if (commandFormat != null) {
            consumer = commandFactory.apply(commandFormat.getName());
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
