package ru.dev.ToDoList.service;

import ru.dev.ToDoList.model.User;

import java.util.Optional;

public interface UserService {
    Optional<User> getUserByName(String username);
}
