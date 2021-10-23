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
        if (tasks.isEmpty()) {
            tasks.add(new Task(1, desc));
        } else {
            tasks.add(new Task(tasks.get(tasks.size() - 1).getId() + 1, desc));
        }
    }

    public int searchById(int specifiedId) {
        int low = 0;
        int high = tasks.size() - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (tasks.get(mid).getId() < specifiedId) {
                low = mid + 1;
            } else if (tasks.get(mid).getId() > specifiedId) {
                high = mid - 1;
            } else if (tasks.get(mid).getId() == specifiedId) {
                return mid;
            }
        }
        return -1;
    }

    public void remove(int index) {
        tasks.remove(index);
    }

    public void print(boolean allPrinted) {
        for (Task curTask : tasks) {
            if (allPrinted || !curTask.isCompleted()) {
                curTask.print();
            }
        }
    }
}