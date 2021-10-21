package model;

public class Task {
    private int id;
    private boolean isCompleted;
    private String description;

    public Task(int taskId, String desc) {
        id = taskId;
        isCompleted = false;
        description = desc;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void print() {
        System.out.printf("%d. [%s] %s\n", id, isCompleted ? "x" : " ", description);
    }

    public void toggle() {
        isCompleted = !isCompleted;
    }
}
