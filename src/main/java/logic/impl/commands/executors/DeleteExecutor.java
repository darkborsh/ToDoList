package logic.impl.commands.executors;

import logic.TaskDao;
import model.Task;
import parser.CommandFormat;

import java.util.Optional;
import java.util.function.BiConsumer;

public class DeleteExecutor implements BiConsumer<CommandFormat, TaskDao> {
    @Override
    public void accept(CommandFormat commandFormat, TaskDao taskDao) {
        taskDao.delete(commandFormat.getId());
    }
}
