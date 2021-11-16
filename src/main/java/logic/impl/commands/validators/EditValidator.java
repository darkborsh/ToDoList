package logic.impl.commands.validators;

import logic.ErrorHandler;
import parser.CommandFormat;

import java.util.function.Predicate;

public class EditValidator extends BaseValidator implements Predicate<CommandFormat> {
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
