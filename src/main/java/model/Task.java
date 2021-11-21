package model;

import lombok.Data;

@Data
public class Task {
    private boolean isCompleted;
    private long id;
    private String description;

    public Task(String description) {
        isCompleted = false;
        this.description = description;
    }
}