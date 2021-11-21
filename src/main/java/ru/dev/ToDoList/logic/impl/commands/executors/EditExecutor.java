package ru.dev.ToDoList.logic.impl.commands.executors;

import org.springframework.stereotype.Component;
import ru.dev.ToDoList.logic.TaskDao;
import ru.dev.ToDoList.model.Task;
import ru.dev.ToDoList.model.CommandFormat;

import java.util.Optional;
import java.util.function.BiConsumer;

@Component
public class EditExecutor implements BiConsumer<CommandFormat, TaskDao> {
    @Override
    public void accept(CommandFormat commandFormat, TaskDao taskDao) {
        Optional<Task> task = taskDao.get(commandFormat.getId());
        task.ifPresent(value -> value.setDescription(commandFormat.getText().trim()));
    }
}
