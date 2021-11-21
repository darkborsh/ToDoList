package logic.impl.commands.executors;

import logic.TaskDao;
import presenters.TaskPrinter;
import model.CommandFormat;

import java.util.function.BiConsumer;

public class SearchExecutor implements BiConsumer<CommandFormat, TaskDao> {
    private final TaskPrinter taskPrinter;

    public SearchExecutor(TaskPrinter taskPrinter) {
        this.taskPrinter = taskPrinter;
    }

    @Override
    public void accept(CommandFormat commandFormat, TaskDao taskDao) {
        String args = commandFormat.getArgs();
        taskPrinter.print(taskDao.find(args, false));
    }
}
