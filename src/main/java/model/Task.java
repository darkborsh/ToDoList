package model;

public class Task {
    private boolean isComplete;
    private String description;

    public Task(String desc) {
        isComplete = false;
        description = desc;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void print() {
        System.out.print("[");
        if (isComplete) {
            System.out.print("x");
        } else {
            System.out.print(" ");
        }
        System.out.print("] ");
        System.out.println(description);
    }

    public void toggle() {
        isComplete = !isComplete;
    }
}
