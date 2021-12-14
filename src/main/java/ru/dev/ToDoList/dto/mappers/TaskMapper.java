package ru.dev.ToDoList.dto.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.dev.ToDoList.dto.TaskDto;
import ru.dev.ToDoList.model.Task;

import java.util.List;

@Mapper
public interface TaskMapper {
    TaskMapper INSTANCE = Mappers.getMapper( TaskMapper.class );

    TaskDto toDto(Task task);
    List<TaskDto> toDtoList (List<Task> taskList);
}
