package ru.dev.ToDoList.dto.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.dev.ToDoList.dto.TaskDto;
import ru.dev.ToDoList.model.Task;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    @Mapping(source = "user.name", target = "user")
    TaskDto toDto(Task task);
    List<TaskDto> toDtoList (List<Task> taskList);
}
