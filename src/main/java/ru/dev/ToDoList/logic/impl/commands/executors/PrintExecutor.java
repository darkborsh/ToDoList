package ru.dev.ToDoList.logic.impl.commands.executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.dev.ToDoList.logic.TaskDao;
import ru.dev.ToDoList.presenters.TaskPrinter;
import ru.dev.ToDoList.model.CommandFormat;

import java.util.function.BiConsumer;

@Component
public class PrintExecutor implements BiConsumer<CommandFormat, TaskDao> {
    private final TaskPrinter taskPrinter;
    @Autowired
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
