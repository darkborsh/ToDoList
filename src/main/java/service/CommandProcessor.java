package service;

import java.util.Map;
import java.util.HashMap;

public class CommandProcessor {
    final static String COMMAND_ADD = "add";
    final static String COMMAND_PRINT = "print";
    final static String COMMAND_TOGGLE = "toggle";
    final static String COMMAND_DELETE = "delete";
    final static String COMMAND_EDIT = "edit";
    final static String COMMAND_SEARCH = "search";
    final static String COMMAND_QUIT = "quit";

    /*Map<String, СommandExecutor> executors;

    public CommandProcessor() {
        executors = new HashMap<>();
        executors.put(COMMAND_ADD, AddExecutor);
        executors.put(COMMAND_PRINT, PrintExecutor);
        executors.put(COMMAND_TOGGLE, ToggleExecutor);
        executors.put(COMMAND_DELETE, DeleteExecutor);
        executors.put(COMMAND_EDIT, EditExecutor);
        executors.put(COMMAND_SEARCH, SearchExecutor);
        executors.put(COMMAND_QUIT, QuitExecutor);
    }

    public boolean process(String commandName, String commandArg) {
        CommandExecutor executor = executors.getKey(commandName);
        if (executor != null) {
            executor.execute(commandArg);
            return true;
        }
        return false;
    }*/
}
