package logic.impl.commands.validators;

import presenters.ErrorHandler;
import model.CommandFormat;

import java.util.function.Predicate;

public class QuitValidator extends BaseValidator implements Predicate<CommandFormat> {
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
