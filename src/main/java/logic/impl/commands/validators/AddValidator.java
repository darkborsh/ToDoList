package logic.impl.commands.validators;

import java.util.function.Predicate;

import logic.ErrorHandler;
import parser.CommandFormat;

public class AddValidator extends BaseValidator implements Predicate<CommandFormat> {
    public AddValidator(ErrorHandler errorHandler) {
        super(errorHandler);
    }

    @Override
    public boolean test(CommandFormat commandFormat) {
        String args = commandFormat.getArgs();
        if (args == null || args.isEmpty()) {
            errorHandler.handle("Задача должна содержать описание");
            return false;
        }
        return true;
    }
}
