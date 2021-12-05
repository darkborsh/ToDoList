package ru.dev.ToDoList.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class Task {
    private boolean isCompleted;
    private long id;
    @NotBlank
    private String description;

    public Task(long id, String description) {
        this.id = id;
        isCompleted = false;
        this.description = description;
    }
}