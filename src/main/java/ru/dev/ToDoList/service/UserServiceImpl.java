package ru.dev.ToDoList.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.dev.ToDoList.dao.UserDao;
import ru.dev.ToDoList.dto.UserDto;
import ru.dev.ToDoList.dto.mappers.UserMapper;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<UserDto> getUsers() {
        return userMapper.toDtoList(userDao.findAll());
    }

    @Override
    public void delete(long userId) {
        userDao.deleteById(userId);
    }

    @Override
    public UserDto save(UserDto userDto) {
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return userMapper.toDto(userDao.save(userMapper.dtoToUser(userDto)));
    }

    @Override
    public Optional<UserDto> getUserByName(String username) {
        return Optional.of(userMapper.toDto(userDao.findByName(username)));
    }
}
