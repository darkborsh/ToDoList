package commands;

import model.Task;
import model.TaskPrinter;

import java.util.Map;

public class SearchExecutor implements CommandExecutor {
    @Override
    public boolean execute(String commandArg, Map<String, Task> data) {
        if (commandArg.equals("")) {
            Helper.help(CommandNames.COMMAND_SEARCH, ErrTypes.EMPTY_ARGUMENT);
        } else {
            data.entrySet().stream()
                    .filter(t -> t.getValue().getDescription().contains(commandArg))
                    .forEach(TaskPrinter::print);
        }
        return true;
    }
}