package ru.dev.ToDoList.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.dev.ToDoList.dao.TaskDao;
import ru.dev.ToDoList.model.Task;

import java.util.ArrayList;
import java.util.List;

@Component
public class TaskManager implements TaskDao {
    private final List<Task> tasks = new ArrayList<>();
    private long taskCounter = 0;
    private final ErrorHandler errorHandler;

    @Autowired
    public TaskManager(ErrorHandler errorHandler) {
        this.errorHandler = errorHandler;
    }

    @Override
    public void add(TaskDescription taskDescription) {
        tasks.add(new Task(taskCounter + 1, taskDescription.get()));
        taskCounter++;
    }

    @Override
    public void toggle(long id) {
        Task task = tasks.get((int) (id - 1));

        if (task == null) {

        } else {
            task.setCompleted(!task.isCompleted());
        }
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public void edit(long id, TaskDescription taskDescription) {

    }

    @Override
    public List<Task> getList(boolean isAll, String substring) {
        return null;
    }
}
