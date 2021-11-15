package commands;

import ErrTypes;
import Helper;
import model.Task;

import java.util.Map;

public class QuitExecutor implements CommandExecutor {
    @Override
    public boolean execute(String commandArg, Map<String, Task> data) {
        if (commandArg.equals("")) {
            return false;
        } else {
            Helper.help(CommandNames.COMMAND_QUIT, ErrTypes.INVALID_ARGUMENT);
            return true;
        }
    }
}