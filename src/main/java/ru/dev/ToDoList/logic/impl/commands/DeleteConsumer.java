package ru.dev.ToDoList.logic.impl.commands;

import org.springframework.stereotype.Component;
import ru.dev.ToDoList.logic.TaskDao;
import ru.dev.ToDoList.model.CommandFormat;

import java.util.function.BiConsumer;

@Component
public class DeleteConsumer implements BiConsumer<CommandFormat, TaskDao> {
    public static final String NAME = "delete";

    @Override
    public void accept(CommandFormat commandFormat, TaskDao taskDao) {
        taskDao.delete(commandFormat.getId());
    }
}
