package model;

import java.util.Map;

public interface CommandExecutor {
    boolean execute(String commandArg, Map<String, Task> data);
}