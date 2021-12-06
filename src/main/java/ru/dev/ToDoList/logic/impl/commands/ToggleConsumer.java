package ru.dev.ToDoList.logic.impl.commands;

import org.springframework.stereotype.Component;
import ru.dev.ToDoList.logic.TaskDao;
import ru.dev.ToDoList.model.CommandFormat;

import java.util.function.BiConsumer;

@Component
public class ToggleConsumer implements BiConsumer<CommandFormat, TaskDao> {
    public static final String NAME = "toggle";

    @Override
    public void accept(CommandFormat commandFormat, TaskDao taskDao) {
        taskDao.get(commandFormat.getId()).ifPresent(task -> task.setCompleted(!task.isCompleted()));
    }
}
