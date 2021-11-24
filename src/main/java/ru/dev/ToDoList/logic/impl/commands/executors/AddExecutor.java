package ru.dev.ToDoList.logic.impl.commands.executors;

import org.springframework.stereotype.Component;
import ru.dev.ToDoList.logic.TaskDao;
import ru.dev.ToDoList.model.Task;
import ru.dev.ToDoList.model.CommandFormat;

import java.util.function.BiConsumer;

@Component
public class AddExecutor implements BiConsumer<CommandFormat, TaskDao> {
    @Override
    public void accept(CommandFormat commandFormat, TaskDao taskDao) {
        taskDao.save(new Task(commandFormat.getArgs()));
    }
}
