package ru.dev.ToDoList.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskDto {
    private long id;
    private String description;
    private boolean isCompleted;
}
