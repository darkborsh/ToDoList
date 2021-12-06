package ru.dev.ToDoList.service;

import org.springframework.stereotype.Component;
import ru.dev.ToDoList.dao.TaskDao;
import ru.dev.ToDoList.model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class TaskDaoImpl implements TaskDao {
    private final List<Task> tasks = new ArrayList<>();
    private long taskCounter = 0;

    @Override
    public Optional<Task> get(long id) {
        return Optional.of(tasks.get((int) (id - 1)));
    }

    @Override
    public void save(Task task) {
        task.setId(taskCounter++);
        tasks.add(task);
    }

    @Override
    public void delete(long id) {
        tasks.remove((int) (id - 1));
    }

    @Override
    public List<Task> getList(boolean isAll, String substring) {
        Stream<Task> stream = tasks.stream();

        if (!isAll) {
            stream = stream.filter(t -> !t.isCompleted());
        }

        if (substring != null) {
            stream = stream.filter(t -> t.getDescription().contains(substring));
        }

        return stream.collect(Collectors.toList());
    }
}
