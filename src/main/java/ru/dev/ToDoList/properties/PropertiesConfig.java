package ru.dev.ToDoList.properties;

import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ru.dev.ToDoList.logic.TaskDao;
import ru.dev.ToDoList.logic.impl.commands.CommandNames;
import ru.dev.ToDoList.logic.impl.commands.executors.*;
import ru.dev.ToDoList.logic.impl.commands.validators.*;
import ru.dev.ToDoList.model.CommandFormat;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

@Configuration
public class PropertiesConfig {
    @Bean(name = "commands")
    Map<String, Pair<Predicate<CommandFormat>, BiConsumer<CommandFormat, TaskDao>>> commands(
            @Autowired
            final TextValidator textValidator, final AddExecutor addExecutor,
            final SearchExecutor searchExecutor,
            final PrintValidator printValidator, final PrintExecutor printExecutor,
            final IdValidator idValidator, final ToggleExecutor toggleExecutor,
            final DeleteExecutor deleteExecutor,
            final EditValidator editValidator, final EditExecutor editExecutor,
            final QuitValidator quitValidator, final QuitExecutor quitExecutor
            ) {
        Map<String, Pair<Predicate<CommandFormat>, BiConsumer<CommandFormat, TaskDao>>> map = new HashMap<>();
        map.put(CommandNames.COMMAND_ADD, new Pair<>(textValidator, addExecutor));
        map.put(CommandNames.COMMAND_PRINT, new Pair<>(printValidator, printExecutor));
        map.put(CommandNames.COMMAND_EDIT, new Pair<>(editValidator, editExecutor));
        map.put(CommandNames.COMMAND_SEARCH, new Pair<>(textValidator, searchExecutor));
        map.put(CommandNames.COMMAND_TOGGLE, new Pair<>(idValidator, toggleExecutor));
        map.put(CommandNames.COMMAND_DELETE, new Pair<>(idValidator, deleteExecutor));
        map.put(CommandNames.COMMAND_QUIT, new Pair<>(quitValidator, quitExecutor));
        return map;
    }
}
