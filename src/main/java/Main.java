import presenters.ErrorHandler;
import logic.TaskDao;
import presenters.impl.ErrorHandlerImpl;
import logic.impl.TaskDaoImpl;
import logic.impl.commands.CommandConsumer;
import logic.impl.commands.StaticCommandFactory;
import model.CommandFormat;
import presenters.impl.parser.InputProcessor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        final InputProcessor inputProcessor = new InputProcessor();
        final ErrorHandler errorHandler = new ErrorHandlerImpl();
        final TaskDao taskDao = new TaskDaoImpl();
        final StaticCommandFactory commandFactory = new StaticCommandFactory(errorHandler);
        final Consumer<CommandFormat> consumer = new CommandConsumer(commandFactory, errorHandler, taskDao);
        reader.lines()
                .map(inputProcessor)
                .forEach(consumer);
    }
}
