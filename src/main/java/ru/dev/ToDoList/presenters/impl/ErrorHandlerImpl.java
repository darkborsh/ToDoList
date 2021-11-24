package ru.dev.ToDoList.presenters.impl;

import org.springframework.stereotype.Component;
import ru.dev.ToDoList.presenters.ErrorHandler;

@Component
public class ErrorHandlerImpl implements ErrorHandler {
    @Override
    public void handle(String error) {
        System.out.println(error);
    }
}
