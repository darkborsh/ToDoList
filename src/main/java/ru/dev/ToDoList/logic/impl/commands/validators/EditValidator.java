package ru.dev.ToDoList.logic.impl.commands.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.dev.ToDoList.presenters.ErrorHandler;
import ru.dev.ToDoList.model.CommandFormat;

import java.util.function.Predicate;

@Component
public class EditValidator extends BaseValidator implements Predicate<CommandFormat> {
    @Autowired
    public EditValidator(ErrorHandler errorHandler) {
        super(errorHandler);
    }

    @Override
    public boolean test(CommandFormat commandFormat) {
        String text = commandFormat.getText();
        if (commandFormat.getId() == 0) {
            errorHandler.handle("Не указан идентификатор задачи");
            return false;
        } else if (text == null || text.isEmpty()) {
            errorHandler.handle("Описание задачи не может быть пустым");
            return false;
        }
        return true;
    }
}
