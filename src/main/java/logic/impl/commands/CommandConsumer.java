package logic.impl.commands;

import javafx.util.Pair;
import presenters.ErrorHandler;
import logic.TaskDao;
import model.CommandFormat;

import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class CommandConsumer implements Consumer<CommandFormat> {
    private final Map<String, Pair<Predicate<CommandFormat>, BiConsumer<CommandFormat, TaskDao>>> commands;
    private final ErrorHandler errorHandler;
    private final TaskDao taskDao;

    public CommandConsumer(Supplier<Map<String, Pair<Predicate<CommandFormat>,
            BiConsumer<CommandFormat, TaskDao>>>> commandsSupplier,
                           ErrorHandler errorHandler,
                           TaskDao taskDao) {
        commands = commandsSupplier.get();
        this.errorHandler = errorHandler;
        this.taskDao = taskDao;
    }

    @Override
    public void accept(CommandFormat commandFormat) {
        Pair<Predicate<CommandFormat>, BiConsumer<CommandFormat, TaskDao>> consumer = null;
        if (commandFormat != null) {
            consumer = commands.get(commandFormat.getName());
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
