package ru.dev.ToDoList.presenters.errors;

import lombok.Data;

@Data
public class EmptyListError {
    private final String error;
}
