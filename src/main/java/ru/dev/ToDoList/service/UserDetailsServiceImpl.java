/*package ru.dev.ToDoList.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import ru.dev.ToDoList.dao.UserDao;
import ru.dev.ToDoList.model.User;


import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<User> user = userDao.findByName(username);

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.get().getName())
                .password(user.get().getPassword())
                .roles(String.valueOf(user.get().getRole()))
                .build();
    }
}
*/