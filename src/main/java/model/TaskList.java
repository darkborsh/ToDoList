package model;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>(1);
    }

    public int size() {
        return tasks.size();
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public void add(String desc) {
        if (tasks.size() == 0) tasks.add(new Task(desc));
        else tasks.set(0, new Task(desc));
    }

    public void print() {
        for (Task task : tasks) {
            task.print();
        }
    }

    public void print_unchecked() {
        for (Task task : tasks) {
            if (!task.get_status()) {
                task.print();
            }
        }
    }
}