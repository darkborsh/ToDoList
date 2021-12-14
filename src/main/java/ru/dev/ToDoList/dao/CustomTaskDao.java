package ru.dev.ToDoList.dao;

import ru.dev.ToDoList.model.Task;

import java.util.List;

public interface CustomTaskDao {
    List<Task> find(long userId, String substring, boolean includeCompleted);
}
