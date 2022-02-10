package ru.dev.ToDoList.service;

import ru.dev.ToDoList.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserDto> getUsers();
    Optional<UserDto> getUserByName(String username);
    UserDto save(UserDto toDto);
    void delete(long userId);
}
