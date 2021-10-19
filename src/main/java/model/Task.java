package model;

public class Task {
    private boolean isCompleted;
    private String description;

    public Task(String desc) {
        isCompleted = false;
        description = desc;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void print() {
        System.out.print("[");
        if (isCompleted) {
            System.out.print("x");
        } else {
            System.out.print(" ");
        }
        System.out.print("] ");
        System.out.println(description);
    }

    public void toggle() {
        isCompleted = !isCompleted;
    }
}
