package commands;

import java.util.Map;
import model.Task;

public class AddExecutor implements CommandExecutor {
    int dataCounter = 0;

    @Override
    public boolean execute(String commandArg, Map<String, Task> data) {
        if (commandArg.equals("")) {
            Helper.help(CommandNames.COMMAND_ADD, ErrTypes.EMPTY_ARGUMENT);
        } else {
            dataCounter++;
            data.put(String.valueOf(dataCounter), new Task(commandArg));
        }
        return true;
    }
}