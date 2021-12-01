package ru.dev.ToDoList.dao;

import ru.dev.ToDoList.model.Task;

import java.util.List;

public interface TaskDao {
    void add(TaskDescription taskDescription);
    void toggle(long id);
    void delete(long id);
    void edit(long id, TaskDescription taskDescription);
    List<Task> getList(boolean isAll, String substring);
}
