package ru.dev.ToDoList.dao;

import org.springframework.data.repository.CrudRepository;
import ru.dev.ToDoList.model.User;

import java.util.Optional;

public interface UserDao extends CrudRepository<User, Long> {
    Optional<User> findByName(String username);
}
