package commands;

import model.ErrTypes;
import model.Helper;
import model.Task;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EditExecutor implements CommandExecutor {
    @Override
    public boolean execute(String commandArg, Map<String, Task> data) {
        Pattern pattern = Pattern.compile("^(\\d+)\\s+(\\S+.*)$");
        Matcher matcher = pattern.matcher(commandArg);
        if (matcher.find()) {
            String id = matcher.group(1);
            String newDescription = matcher.group(2);
            Task task = data.get(id);
            if (task != null) {
                data.get(id).setDescription(newDescription);
            } else {
                Helper.help(CommandNames.COMMAND_EDIT, ErrTypes.INVALID_ARGUMENT);
            }
        } else {
            Helper.help(CommandNames.COMMAND_EDIT, ErrTypes.INVALID_ARGUMENT);
        }
        return true;
    }
}