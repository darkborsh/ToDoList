package ru.dev.ToDoList.dto.mappers;

import org.mapstruct.Mapper;
import ru.dev.ToDoList.dto.TaskDto;
import ru.dev.ToDoList.model.Task;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    TaskDto toDto(Task task);
    Task dtoToTask(TaskDto dto);
    List<TaskDto> toDtoList (List<Task> taskList);
    List<Task> toTaskList (List<TaskDto> dtoList);
}