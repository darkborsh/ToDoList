package ru.dev.ToDoList.service;

import ru.dev.ToDoList.dto.UserDto;
import ru.dev.ToDoList.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserDto> getUsers();
    Optional<User> getUserByName(String username);
    void save(UserDto toDto);
    void delete(long userId);
}
