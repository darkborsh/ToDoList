package ru.dev.ToDoList.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dev.ToDoList.dao.UserDao;
import ru.dev.ToDoList.model.User;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    @Override
    public Optional<User> getUserByName(String username) {
        return userDao.findByName(username);
    }
}
