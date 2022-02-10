package ru.dev.ToDoList.dao;

import ru.dev.ToDoList.model.Task;
import ru.dev.ToDoList.model.User;

import java.util.List;
import java.util.function.Consumer;

public interface CustomTaskDao {
    Task findByIdAndUserId(long id, long userId);
    List<Task> find(long userId, String substring, boolean includeCompleted);
    Task build(String description, User user);
    boolean update(long taskId, long userId, Consumer<Task> command);
}
