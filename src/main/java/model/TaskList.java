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
        if (tasks.isEmpty()) tasks.add(new Task(1, desc));
        else tasks.set(0, new Task(1, desc));
    }

    public void print(boolean allPrinted) {
        for (Task curTask : tasks) {
            if (allPrinted || !curTask.isCompleted()) {
                curTask.print();
            }
        }
    }
}