package ru.dev.ToDoList.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dev.ToDoList.model.Task;

import java.util.List;

public interface TaskDao extends JpaRepository<Task, Long> {
    List<Task> findAllByIsCompleted(boolean isCompleted);
    List<Task> findAllByDescriptionIsLike(String substring);
}
