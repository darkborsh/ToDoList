package presenters.impl.parser;

import model.CommandFormat;

import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputProcessor implements Function<String, CommandFormat> {
    private final Pattern COMMAND_FORMAT =
            Pattern.compile("\\s*(?<cmd>\\w+)(?:\\s+(?<args>(?:(?<id>\\d+)\\b)?(?<text>.*)))?");

    @Override
    public CommandFormat apply(String userInput) {
        Matcher cmdMatcher = COMMAND_FORMAT.matcher(userInput);
        if (cmdMatcher.find()) {
            CommandFormat.CommandFormatBuilder builder = CommandFormat.builder()
                    .name(cmdMatcher.group("cmd"))
                    .args(cmdMatcher.group("args"))
                    .text(cmdMatcher.group("text"));
            String taskId = cmdMatcher.group("id");
            if (taskId != null) {
                builder.id(Long.parseLong(taskId));
            }
            return builder.build();
        }
        return null;
    }
}
