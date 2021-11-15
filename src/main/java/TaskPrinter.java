import model.Task;

import java.util.Map;

public class TaskPrinter {
    public static void print(Map.Entry<String, Task> stringTaskEntry) {
        System.out.printf("%s. [%s] %s\n",
                stringTaskEntry.getKey(),
                stringTaskEntry.getValue().isCompleted() ? "x" : " ",
                stringTaskEntry.getValue().getDescription());
    }
}