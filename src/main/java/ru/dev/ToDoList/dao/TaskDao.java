package ru.dev.ToDoList.dao;

import ru.dev.ToDoList.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskDao {
    Optional<Task> get(long id);
    void save(Task task);
    void delete(long id);
    List<Task> getList(boolean isAll, String substring);
}
