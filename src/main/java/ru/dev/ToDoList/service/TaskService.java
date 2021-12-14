package ru.dev.ToDoList.service;

import ru.dev.ToDoList.dto.TaskDto;

import java.util.List;

public interface TaskService {
    List<TaskDto> getAll(String substring, boolean includeCompleted, long userId);
    //TaskDto get(long taskId, long userId);
    void save(TaskDto taskDto, long userId);
    void delete(long taskId, long userId);
    void updateDescription(long taskId, String newDescription, long userId);
    void updateStatus(long taskId, boolean status, long userId);
}
