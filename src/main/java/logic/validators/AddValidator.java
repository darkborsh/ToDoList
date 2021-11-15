package logic.validators;

import java.util.function.Predicate;

import logic.ErrorHandler;
import service.CommandDescription;

public class AddValidator implements Predicate<CommandDescription> {
    private final ErrorHandler errorHandler;

    public AddValidator(ErrorHandler errorHandler) {
        this.errorHandler = errorHandler;
    }

    @Override
    public boolean test(CommandDescription commandDescription) {
        if (commandDescription.getText().equals("") || commandDescription.getText() == null) {
            errorHandler.handle("Задача должна содержать описание");
            return false;
        }
        return true;
    }
}
