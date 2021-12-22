package ru.dev.ToDoList.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dev.ToDoList.dao.UserDao;
import ru.dev.ToDoList.dto.UserDto;
import ru.dev.ToDoList.dto.mappers.UserMapper;
import ru.dev.ToDoList.model.User;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final UserMapper userMapper;

    @Override
    public List<UserDto> getUsers() {
        return userMapper.toDtoList(userDao.findAll());
    }

    @Override
    public void delete(long userId) {
        userDao.deleteById(userId);
    }

    @Override
    public void save(UserDto userDto) {
        userDao.save(userMapper.dtoToUser(userDto));
    }

    @Override
    public Optional<User> getUserByName(String username) {
        return userDao.findByName(username);
    }
}
