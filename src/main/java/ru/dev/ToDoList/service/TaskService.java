package ru.dev.ToDoList.service;

import ru.dev.ToDoList.dto.TaskDto;
import ru.dev.ToDoList.model.DescriptionHolder;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    List<TaskDto> getAll(String substring, boolean includeCompleted);
    TaskDto save(DescriptionHolder description);
    void delete(long taskId);
    boolean updateDescription(long taskId, String newDescription);
    boolean updateStatus(long taskId, boolean status);
}
