package ru.dev.ToDoList.logic.impl.commands.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.dev.ToDoList.presenters.ErrorHandler;
import ru.dev.ToDoList.model.CommandFormat;

import java.util.function.Predicate;

@Component
public class QuitValidator extends BaseValidator implements Predicate<CommandFormat> {
    @Autowired
    public QuitValidator(ErrorHandler errorHandler) {
        super(errorHandler);
    }

    @Override
    public boolean test(CommandFormat commandFormat) {
        String args = commandFormat.getArgs();
        if (args != null && !args.isEmpty()) {
            errorHandler.handle("У quit не должно быть описания");
            return false;
        }
        return true;
    }
}
