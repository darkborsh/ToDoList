package logic.impl.commands.validators;

import presenters.ErrorHandler;

public abstract class BaseValidator {
    protected final ErrorHandler errorHandler;

    protected BaseValidator(ErrorHandler errorHandler) {
        this.errorHandler = errorHandler;
    }
}
