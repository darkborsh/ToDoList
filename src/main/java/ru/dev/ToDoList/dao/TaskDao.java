package ru.dev.ToDoList.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.dev.ToDoList.model.Task;

@Repository
public interface TaskDao extends CrudRepository<Task, Long>, CustomTaskDao {

}
