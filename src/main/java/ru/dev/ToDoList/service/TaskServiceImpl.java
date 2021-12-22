package ru.dev.ToDoList.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.dev.ToDoList.dao.TaskDao;
import ru.dev.ToDoList.dto.TaskDto;
import ru.dev.ToDoList.dto.mappers.TaskMapper;
import ru.dev.ToDoList.model.Task;
import ru.dev.ToDoList.model.User;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@RequiredArgsConstructor
@Service
public class TaskServiceImpl implements TaskService {
    private final TaskDao taskDao;
    private final TaskMapper taskMapper;
    private final UserService userService;

    private String getUsernameByContext() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    private User getUserByContext() {
        return userService.getUserByName(getUsernameByContext()).get();
    }

    private Optional<Task> get(long taskId) {
        return Optional.of(taskDao.findByIdAndUserId(taskId, getUserByContext().getId()));
    }

    @Override
    public List<TaskDto> getAll(String substring, boolean includeCompleted) {
        return taskMapper.toDtoList(taskDao.find(getUserByContext().getId(), substring, includeCompleted));
    }

    @Override
    public Task save(TaskDto taskDto) {
        Task t = new Task();
        t.setDescription(taskDto.getDescription());
        t.setCompleted(taskDto.isCompleted());
        t.setUser(this.getUserByContext());
        taskDao.save(t);
        return t;
    }

    @Override
    public void delete(long taskId) {
        if (this.get(taskId).isPresent()) {
            taskDao.deleteById(taskId);
        }
    }

    private boolean actionUpdate(long taskId, Consumer<Task> command) {
        Optional<Task> task = this.get(taskId);
        if (task.isPresent()) {
            command.accept(task.get());
            taskDao.save(task.get());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean updateDescription(long taskId, String newDescription) {
        return actionUpdate(taskId, t -> t.setDescription(newDescription));
    }

    @Override
    public boolean updateStatus(long taskId, boolean status) {
        return actionUpdate(taskId, t -> t.setCompleted(status));
    }
}
