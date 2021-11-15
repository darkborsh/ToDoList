package commands;

import ErrTypes;
import Helper;
import model.Task;
import TaskPrinter;

import java.util.Map;
import java.util.stream.Stream;

public class PrintExecutor implements CommandExecutor {
    @Override
    public boolean execute(String commandArg, Map<String, Task> data) {
        boolean allPrinted = commandArg.equals("all");
        if (allPrinted || commandArg.equals("")) {
            Stream<Map.Entry<String, Task>> stream = data.entrySet().stream();
            if (!allPrinted) {
                stream = stream.filter(s -> !s.getValue().isCompleted());
            }
            stream.forEach(TaskPrinter::print);
        } else {
            Helper.help(CommandNames.COMMAND_PRINT, ErrTypes.INVALID_ARGUMENT);
        }
        return true;
    }
}