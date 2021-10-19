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
        for (int i = 0; i < tasks.size(); i++) {
            System.out.print(i + 1 + ". ");
            tasks.get(i).print();
        }
    }

    public void printIncomplete() {
        for (int i = 0; i < tasks.size(); i++) {
            if (!tasks.get(i).isComplete()) {
                System.out.print(i + 1 + ". ");
                tasks.get(i).print();
            }
        }
    }
}