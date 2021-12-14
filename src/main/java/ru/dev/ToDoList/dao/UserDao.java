package ru.dev.ToDoList.dao;

import org.springframework.data.repository.CrudRepository;
import ru.dev.ToDoList.model.User;

public interface UserDao extends CrudRepository<User, Long> {

}
