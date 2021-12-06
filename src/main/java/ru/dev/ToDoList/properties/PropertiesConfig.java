package ru.dev.ToDoList.properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.dev.ToDoList.logic.Command;
import ru.dev.ToDoList.logic.impl.commands.DeleteConsumer;
import ru.dev.ToDoList.logic.impl.commands.TaskIdCommand;
import ru.dev.ToDoList.logic.impl.commands.ToggleConsumer;

@Configuration
public class PropertiesConfig {
    @Bean
    public Command toggleCommand(ToggleConsumer toggleConsumer) {
        return new TaskIdCommand(toggleConsumer, ToggleConsumer.NAME);
    }

    @Bean
    public Command deleteCommand(DeleteConsumer deleteConsumer) {
        return new TaskIdCommand(deleteConsumer, DeleteConsumer.NAME);
    }
}
