package ru.dev.ToDoList.presenters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.dev.ToDoList.presenters.errors.EmptyListError;

@ControllerAdvice
@Slf4j
public class ErrorHandler {
    @ExceptionHandler
    public ResponseEntity<String> handle(EmptyListError e) {
        log.error("Error: {}", e);
        return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
    }
}
