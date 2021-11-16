package logic.impl.commands.validators;

import java.util.function.Predicate;

import logic.ErrorHandler;
import parser.CommandFormat;

public class TextValidator extends BaseValidator implements Predicate<CommandFormat> {
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
