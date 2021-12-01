package ru.dev.ToDoList.model;

import lombok.Data;

@Data
public class Task {
    private boolean isCompleted;
    private long id;
    private String description;

    public Task(long id, String description) {
        this.id = id;
        isCompleted = false;
        this.description = description;
    }
}