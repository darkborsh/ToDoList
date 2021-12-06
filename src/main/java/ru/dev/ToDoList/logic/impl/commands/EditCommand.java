package ru.dev.ToDoList.logic.impl.commands;

import org.springframework.stereotype.Component;
import ru.dev.ToDoList.logic.TaskDao;
import ru.dev.ToDoList.logic.Command;
import ru.dev.ToDoList.model.CommandFormat;
import ru.dev.ToDoList.model.Task;

import java.util.Optional;
import java.util.stream.Stream;

@Component
public class EditCommand implements Command {
    private static final String NAME = "edit";

    @Override
    public Optional<String> validate(CommandFormat cmdFormat) {
        String text = cmdFormat.getText();
        if (cmdFormat.getId() == 0) {
            return Optional.of("Не указан идентификатор задачи");
        } else if (text == null || text.isEmpty()) {
            return Optional.of("Описание задачи не может быть пустым");
        }
        return Optional.empty();
    }

    @Override
    public Stream<Task> apply(CommandFormat cmdFormat, TaskDao taskDao) {
        taskDao.get(cmdFormat.getId()).ifPresent(task -> task.setDescription(cmdFormat.getText().trim()));
        return Stream.empty();
    }

    @Override
    public String getName() {
        return NAME;
    }
}
