package commands;

import java.util.Map;
import model.Task;

public interface CommandExecutor {
    boolean execute(String commandArg, Map<String, Task> data);
}