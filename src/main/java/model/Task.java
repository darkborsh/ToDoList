package model;

public class Task {
    private boolean status;
    private String description;

    public Task(String desc) {
        status = false;
        description = desc;
    }

    public boolean get_status() {
        return status;
    }

    public void print() {
        System.out.print("[");
        if (status) {
            System.out.print("x");
        } else {
            System.out.print(" ");
        }
        System.out.print("] ");
        System.out.println(description);
    }

    public void toggle() {
        status = !status;
    }
}
