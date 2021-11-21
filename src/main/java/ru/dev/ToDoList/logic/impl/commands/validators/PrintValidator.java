package ru.dev.ToDoList.logic.impl.commands.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.dev.ToDoList.presenters.ErrorHandler;
import ru.dev.ToDoList.model.CommandFormat;

import java.util.function.Predicate;

@Component
public class PrintValidator extends BaseValidator implements Predicate<CommandFormat>  {
    @Autowired
    public PrintValidator(ErrorHandler errorHandler) {
        super(errorHandler);
    }

    @Override
    public boolean test(CommandFormat commandFormat) {
        String args = commandFormat.getArgs();
        if (args != null && !args.equals("all") && !args.equals("")) {
            errorHandler.handle("Неверный формат команды print");
            return false;
        }
        return true;
    }
}
