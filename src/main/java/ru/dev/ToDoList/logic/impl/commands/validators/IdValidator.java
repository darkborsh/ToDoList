package ru.dev.ToDoList.logic.impl.commands.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.dev.ToDoList.presenters.ErrorHandler;
import ru.dev.ToDoList.model.CommandFormat;

import java.util.function.Predicate;

@Component
public class IdValidator extends BaseValidator implements Predicate<CommandFormat> {
    @Autowired
    public IdValidator(ErrorHandler errorHandler) {
        super(errorHandler);
    }

    @Override
    public boolean test(CommandFormat commandFormat) {
        if (commandFormat.getId() == 0) {
            errorHandler.handle("Не указан идентификатор задачи");
            return false;
        }
        return true;
    }
}
