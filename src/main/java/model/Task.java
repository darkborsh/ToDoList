package model;

import lombok.Data;

@Data
public class Task {
    private boolean isCompleted;
    private String id;
    private String description;

    public Task(String id, String description) {
        isCompleted = false;
        this.id = id;
        this.description = description;
    }
}