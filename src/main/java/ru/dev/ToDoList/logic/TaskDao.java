package ru.dev.ToDoList.logic;

import ru.dev.ToDoList.model.Task;

import java.util.Optional;
import java.util.stream.Stream;

public interface TaskDao {
    void save(Task task);
    void delete(long id);
    Stream<Task> find(String substring, boolean excludeCompleted);
    Optional<Task> get(long id);
}
