package ru.dev.ToDoList.dto.mappers;

import ru.dev.ToDoList.dto.TaskDto;
import ru.dev.ToDoList.model.Task;

import java.util.List;

public class TaskMapper {
    public TaskDto taskToDto(Task task);
    public List<TaskDto> toDtoList (List<Task> taskList);
}
