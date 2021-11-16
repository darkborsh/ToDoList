package logic.impl.validators;

import logic.ErrorHandler;

public abstract class BaseValidator {
    protected final ErrorHandler errorHandler;

    protected BaseValidator(ErrorHandler errorHandler) {
        this.errorHandler = errorHandler;
    }
}
