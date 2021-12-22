package ru.dev.ToDoList.dto;

import lombok.Getter;
import lombok.Setter;
import ru.dev.ToDoList.model.Role;

@Getter
@Setter
public class UserDto {
    private long id;
    private String name;
    private Role role;
}
