package logic.impl.commands.validators;

import presenters.ErrorHandler;
import model.CommandFormat;

import java.util.function.Predicate;

public class IdValidator extends BaseValidator implements Predicate<CommandFormat> {
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
