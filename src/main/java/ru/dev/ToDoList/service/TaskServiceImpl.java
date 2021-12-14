package ru.dev.ToDoList.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dev.ToDoList.dao.TaskDao;
import ru.dev.ToDoList.dto.TaskDto;
import ru.dev.ToDoList.dto.mappers.TaskMapper;
import ru.dev.ToDoList.model.Task;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TaskServiceImpl implements TaskService {
    private final TaskDao taskDao;
    private final UserService userService;
    private final TaskMapper taskMapper;

    @Override
    public List<TaskDto> getAll(String substring, boolean includeCompleted, long userId) {
        return new taskMapper.INSTANCE.toDtoList(taskDao.find(userId, substring, includeCompleted));
    }

    @Override
    public void save(TaskDto taskDto, long userId) {
        taskDao.save(new Task(taskDto.getDescription(), taskDto.isCompleted(), userService.getUserById(userId)));
    }

    @Override
    public void delete(long taskId, long userId) {

    }

    @Override
    public void updateDescription(long taskId, String newDescription, long userId) {

    }

    @Override
    public void updateStatus(long taskId, boolean status, long userId) {

    }
}
