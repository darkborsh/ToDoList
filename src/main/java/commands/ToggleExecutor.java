package commands;

import java.util.Map;
import model.Task;

public class ToggleExecutor implements CommandExecutor {
    @Override
    public boolean execute(String commandArg, Map<String, Task> data) {
        Task task = data.get(commandArg);
        if (task != null) {
            task.setCompleted(!task.isCompleted());
        } else {
            Helper.help(CommandNames.COMMAND_TOGGLE, ErrTypes.INVALID_ARGUMENT);
        }
        return true;
    }
}
