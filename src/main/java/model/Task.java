package model;

import lombok.Data;

@Data
public class Task {
    private boolean isCompleted;
    private String description;

    public Task(String desc) {
        isCompleted = false;
        description = desc;
    }
}