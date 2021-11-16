package logic.impl.commands.validators;

import logic.ErrorHandler;
import parser.CommandFormat;

import java.util.function.Predicate;

public class PrintValidator extends BaseValidator implements Predicate<CommandFormat>  {
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
