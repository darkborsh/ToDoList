package model;

import lombok.Data;

@Data
public class Task {
    private String id;
    private boolean isCompleted;
    private String description;

    public Task(String newId, String desc) {
        id = newId;
        isCompleted = false;
        description = desc;
    }
}