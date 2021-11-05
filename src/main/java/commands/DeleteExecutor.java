package commands;

import java.util.Map;
import model.Task;

public class DeleteExecutor implements CommandExecutor {
    @Override
    public boolean execute(String commandArg, Map<String, Task> data) {
        if (data.remove(commandArg) == null) {
            Helper.help(CommandNames.COMMAND_DELETE, ErrTypes.INVALID_ARGUMENT);
        }
        return true;
    }
}