import logic.ErrorHandler;
import logic.TaskDao;
import logic.impl.ErrorHandlerImpl;
import logic.impl.TaskDaoImpl;
import logic.impl.commands.CommandConsumer;
import logic.impl.commands.StaticCommandFactory;
import parser.CommandFormat;
import parser.InputProcessor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        final InputProcessor parser = new InputProcessor();
        final ErrorHandler errorHandler = new ErrorHandlerImpl();
        final TaskDao taskDao = new TaskDaoImpl();
        final StaticCommandFactory commandFactory = new StaticCommandFactory(errorHandler);
        final Consumer<CommandFormat> consumer = new CommandConsumer(commandFactory, errorHandler, taskDao);
        reader.lines()
                .map(parser)
                .forEach(consumer);
    }
}
