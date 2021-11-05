package commands;

import model.Task;

import java.util.Map;
import java.util.HashMap;

public class CommandProcessor {
    Map<String, CommandExecutor> executors;

    public CommandProcessor() {
        executors = new HashMap<>();
        executors.put(CommandNames.COMMAND_ADD, new AddExecutor());
        executors.put(CommandNames.COMMAND_PRINT, new PrintExecutor());
        executors.put(CommandNames.COMMAND_TOGGLE, new ToggleExecutor());
        executors.put(CommandNames.COMMAND_DELETE, new DeleteExecutor());
        executors.put(CommandNames.COMMAND_SEARCH, new SearchExecutor());
        /*executors.put(CommandNames.COMMAND_EDIT, new EditExecutor());
        executors.put(CommandNames.COMMAND_QUIT, new QuitExecutor());*/
    }

    public boolean process(String commandName, String commandArg, Map<String, Task> data) {
        CommandExecutor executor = executors.get(commandName);
        if (executor != null) {
            return executor.execute(commandArg, data);
        } else {
            System.out.println("*Invalid command*");
            return true;
        }
    }
}