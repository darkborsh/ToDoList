package ru.dev.ToDoList.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dev.ToDoList.model.User;

@Repository
public interface UserDao extends JpaRepository<User, Long> {
    User findByName(String username);
}
