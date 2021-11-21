package ru.dev.ToDoList.logic.impl.commands.validators;

import ru.dev.ToDoList.presenters.ErrorHandler;

public abstract class BaseValidator {
    protected final ErrorHandler errorHandler;

    protected BaseValidator(ErrorHandler errorHandler) {
        this.errorHandler = errorHandler;
    }
}
