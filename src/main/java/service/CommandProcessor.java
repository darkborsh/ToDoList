package service;

import java.util.Map;
import java.util.HashMap;
import model.Task;
import model.CommandExecutor;
import model.AddExecutor;
import model.PrintExecutor;

public class CommandProcessor {
    final static String COMMAND_ADD = "add";
    final static String COMMAND_PRINT = "print";
    final static String COMMAND_TOGGLE = "toggle";
    final static String COMMAND_DELETE = "delete";
    final static String COMMAND_EDIT = "edit";
    final static String COMMAND_SEARCH = "search";
    final static String COMMAND_QUIT = "quit";

    Map<String, CommandExecutor> executors;

    public CommandProcessor() {
        executors = new HashMap<>();
        executors.put(COMMAND_ADD, new AddExecutor());
        executors.put(COMMAND_PRINT, new PrintExecutor());
        /*executors.put(COMMAND_TOGGLE, new ToggleExecutor());
        executors.put(COMMAND_DELETE, new DeleteExecutor());
        executors.put(COMMAND_EDIT, new EditExecutor());
        executors.put(COMMAND_SEARCH, new SearchExecutor());
        executors.put(COMMAND_QUIT, new QuitExecutor());*/
    }

    public boolean process(String commandName, String commandArg, Map<String, Task> data) {
        CommandExecutor executor = executors.get(commandName);
        if (executor != null) {
            return executor.execute(commandArg, data);
        } else {
            System.out.println("Неверная команда");
            return true;
        }
    }
}