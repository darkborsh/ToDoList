package logic.impl.commands.executors;

import logic.TaskDao;
import model.Task;
import model.CommandFormat;

import java.util.Optional;
import java.util.function.BiConsumer;

public class ToggleExecutor implements BiConsumer<CommandFormat, TaskDao> {
    @Override
    public void accept(CommandFormat commandFormat, TaskDao taskDao) {
        Optional<Task> task = taskDao.get(commandFormat.getId());
        task.ifPresent(value -> value.setCompleted(!value.isCompleted()));
    }
}
