package model;

import java.util.Map;
import java.util.LinkedHashMap;
import java.util.stream.Stream;

public class TaskList {
    private long counter;
    private Map<String, Task> tasks;

    public TaskList() {
        counter = 0;
        tasks = new LinkedHashMap<>();
    }

    public void add(String desc) {
        counter++;
        tasks.put(String.valueOf(counter), new Task(desc));
    }

    public void print(boolean allPrinted) {
        Stream<Map.Entry<String, Task>> stream = tasks.entrySet().stream();
        if (!allPrinted) {
            stream = stream.filter(s -> !s.getValue().isCompleted());
        }
        stream.forEach(Task::print);
    }

    public void search(String substring) {
        tasks.entrySet().stream()
                .filter(t -> t.getValue().getDescription().contains(substring))
                .forEach(Task::print);
    }

    public boolean toggle(String idKey) {
        Task task = tasks.get(idKey);
        if (task != null) {
            task.setCompleted(!task.isCompleted());
            return true;
        } else {
            return false;
        }
    }

    public boolean delete(String idKey) {
        return tasks.remove(idKey) != null;
    }

    public boolean edit(String idKey, String newDescription) {
        Task task = tasks.get(idKey);
        if (task != null) {
            tasks.get(idKey).setDescription(newDescription);
            return true;
        } else {
            return false;
        }
    }
}