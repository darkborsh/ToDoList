package ru.dev.ToDoList.dao;

import org.springframework.data.repository.CrudRepository;
import ru.dev.ToDoList.model.Task;

public interface TaskDao extends CrudRepository<Task, Long>, CustomTaskDao {

}
