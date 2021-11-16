package logic.impl.commands.validators;

import logic.ErrorHandler;
import logic.TaskDao;
import parser.CommandFormat;

import java.util.function.Predicate;

public class IdValidator extends BaseValidator implements Predicate<CommandFormat> {
    public IdValidator(ErrorHandler errorHandler) {
        super(errorHandler);
    }

    @Override
    public boolean test(CommandFormat commandFormat) {
        long id = commandFormat.getId();
        if (id == 0) {
            errorHandler.handle("Не указан идентификатор задачи");
            return false;
        }
        return true;
    }
}
