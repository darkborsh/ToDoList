package model;

import java.util.Map;
import lombok.Data;

@Data
public class Task {
    private boolean isCompleted;
    private String description;

    public Task(String desc) {
        isCompleted = false;
        description = desc;
    }

    public static void print(Map.Entry<String, Task> stringTaskEntry) {
        System.out.printf("%s. [%s] %s\n",
                stringTaskEntry.getKey(),
                stringTaskEntry.getValue().isCompleted() ? "x" : " ",
                stringTaskEntry.getValue().getDescription());
    }
}