package model;

import java.util.Map;

public class AddExecutor implements CommandExecutor {
    int dataCounter = 0;

    @Override
    public boolean execute(String commandArg, Map<String, Task> data) {
        dataCounter++;
        data.put(String.valueOf(dataCounter), new Task(commandArg));
        return true;
    }
}
