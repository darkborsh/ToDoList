package ru.dev.ToDoList.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dev.ToDoList.model.User;

public interface UserDao extends JpaRepository<User, Long> {

}
