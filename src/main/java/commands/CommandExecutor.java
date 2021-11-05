package commands;

import model.Task;

import java.util.Map;

public interface CommandExecutor {
    boolean execute(String commandArg, Map<String, Task> data);
}