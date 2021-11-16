package logic.impl.commands.executors;

import logic.TaskDao;
import parser.CommandFormat;

import java.util.function.BiConsumer;

public class QuitExecutor implements BiConsumer<CommandFormat, TaskDao> {
    @Override
    public void accept(CommandFormat commandFormat, TaskDao taskDao) {
        System.exit(0);
    }
}