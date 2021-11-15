package model;

import lombok.Data;

@Data
public class Task {
    private boolean isCompleted;
    private long id;
    private String description;

    public Task(long id, String description) {
        isCompleted = false;
        this.id = id;
        this.description = description;
    }
}