package ru.dev.ToDoList.logic.impl.commands.validators;

import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.dev.ToDoList.presenters.ErrorHandler;
import ru.dev.ToDoList.model.CommandFormat;

@Component
public class TextValidator extends BaseValidator implements Predicate<CommandFormat> {
    @Autowired
    public TextValidator(ErrorHandler errorHandler) {
        super(errorHandler);
    }

    @Override
    public boolean test(CommandFormat commandFormat) {
        String args = commandFormat.getArgs();
        if (args == null || args.isEmpty()) {
            errorHandler.handle("Описание задачи не может быть пустым");
            return false;
        }
        return true;
    }
}
