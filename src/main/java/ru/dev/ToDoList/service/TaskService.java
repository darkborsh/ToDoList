package ru.dev.ToDoList.service;

import ru.dev.ToDoList.dto.TaskDto;
import ru.dev.ToDoList.model.Task;

import java.util.List;

public interface TaskService {
    List<TaskDto> getAll(String substring, boolean includeCompleted);
    Task save(TaskDto taskDto);
    void delete(long taskId);
    boolean updateDescription(long taskId, String newDescription);
    boolean updateStatus(long taskId, boolean status);
}
