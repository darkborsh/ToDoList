package ru.dev.ToDoList.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CommandFormat {
    private final String name;
    private final String args;
    private final long id;
    private final String text;
}
