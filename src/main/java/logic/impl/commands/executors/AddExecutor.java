package logic.impl.commands.executors;

import logic.TaskDao;
import model.Task;
import model.CommandFormat;

import java.util.function.BiConsumer;

public class AddExecutor implements BiConsumer<CommandFormat, TaskDao> {
    @Override
    public void accept(CommandFormat commandFormat, TaskDao taskDao) {
        taskDao.save(new Task(commandFormat.getArgs()));
    }
}
