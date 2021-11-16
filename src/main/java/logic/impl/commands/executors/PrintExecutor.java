package logic.impl.commands.executors;

import logic.TaskDao;
import presenters.TaskPrinter;
import model.CommandFormat;

import java.util.function.BiConsumer;

public class PrintExecutor implements BiConsumer<CommandFormat, TaskDao> {
    private final TaskPrinter taskPrinter;

    public PrintExecutor(TaskPrinter taskPrinter) {
        this.taskPrinter = taskPrinter;
    }

    @Override
    public void accept(CommandFormat commandFormat, TaskDao taskDao) {
        String args = commandFormat.getArgs();
        boolean excludeCompleted;
        if (args == null || args.equals("")) {
            excludeCompleted = true;
        } else {
            excludeCompleted = !args.equals("all");
        }
        taskPrinter.print(taskDao.find(null, excludeCompleted));
    }
}
