package ru.dev.ToDoList.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.dev.ToDoList.dao.TaskDao;
import ru.dev.ToDoList.dto.TaskDto;
import ru.dev.ToDoList.dto.UserDto;
import ru.dev.ToDoList.dto.mappers.TaskMapper;
import ru.dev.ToDoList.dto.mappers.UserMapper;
import ru.dev.ToDoList.model.DescriptionHolder;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TaskServiceImpl implements TaskService {
    private final TaskDao taskDao;
    private final TaskMapper taskMapper;
    private final UserService userService;
    private final UserMapper userMapper;

    private String getUsernameByContext() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    private Optional<UserDto> getUserByContext() {
        return userService.getUserByName(getUsernameByContext());
    }

    private Optional<TaskDto> get(long taskId) {
        Optional<UserDto> user = getUserByContext();
        return user.map(userDto -> taskMapper.toDto(taskDao.findByIdAndUserId(taskId, userDto.getId())));
    }

    @Override
    public List<TaskDto> getAll(String substring, boolean includeCompleted) {
        Optional<UserDto> user = getUserByContext();
        if (user.isPresent()) {
            return taskMapper.toDtoList(taskDao.find(user.get().getId(), substring, includeCompleted));
        }
        return Collections.emptyList();
    }

    @Override
    public TaskDto save(DescriptionHolder descriptionHolder) {
        return taskMapper.toDto(taskDao.save(taskDao.build(descriptionHolder.getDescription(), userMapper.dtoToUser(getUserByContext().get()))));
    }

    @Override
    public void delete(long taskId) {
        if (this.get(taskId).isPresent()) {
            taskDao.deleteById(taskId);
        }
    }

    @Override
    public boolean updateDescription(long taskId, String newDescription) {
        return taskDao.update(taskId, getUserByContext().get().getId(), t -> t.setDescription(newDescription));
    }

    @Override
    public boolean updateStatus(long taskId, boolean status) {
        return taskDao.update(taskId, getUserByContext().get().getId(), t -> t.setCompleted(status));
    }
}
