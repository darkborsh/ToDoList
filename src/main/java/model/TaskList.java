package model;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public int size() {
        return tasks.size();
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public void add(String desc) {
        tasks.add(new Task(tasks.size() + 1, desc));
    }

    public void print(boolean allPrinted) {
        for (Task curTask : tasks) {
            if (allPrinted || !curTask.isCompleted()) {
                curTask.print();
            }
        }
    }
}