package ru.dev.ToDoList.presenters.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.dev.ToDoList.presenters.ErrorHandler;

@Component
@Slf4j
public class ErrorHandlerImpl implements ErrorHandler {
    @Override
    public void handle(String error) {
        log.error("Error in command: {}", error);
        System.out.println(error);
    }
}
