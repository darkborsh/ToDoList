package ru.dev.ToDoList.logic.impl.commands.executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.dev.ToDoList.logic.TaskDao;
import ru.dev.ToDoList.presenters.TaskPrinter;
import ru.dev.ToDoList.model.CommandFormat;

import java.util.function.BiConsumer;

@Component
public class SearchExecutor implements BiConsumer<CommandFormat, TaskDao> {
    private final TaskPrinter taskPrinter;
    @Autowired
    public SearchExecutor(TaskPrinter taskPrinter) {
        this.taskPrinter = taskPrinter;
    }

    @Override
    public void accept(CommandFormat commandFormat, TaskDao taskDao) {
        String args = commandFormat.getArgs();
        taskPrinter.print(taskDao.find(args, false));
    }
}
