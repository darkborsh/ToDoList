package ru.test.ToDoList.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.http.ResponseEntity;
import ru.dev.ToDoList.controllers.UserController;
import ru.dev.ToDoList.dto.UserDto;
import ru.dev.ToDoList.model.Role;
import ru.dev.ToDoList.service.UserService;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class UserControllerTest {
    private UserService userService;
    private UserController userController;

    @BeforeEach
    public void prepareController() {
        userService = mock(UserService.class);
        userController = new UserController(userService);
    }

    @Test
    public void encoderTest() {
        UserDto userDto = new UserDto();
        userDto.setName("Ivan");
        String passwordBeforeEncoding = "4765";
        userDto.setRole(Role.USER);
        userDto.setPassword(passwordBeforeEncoding);

        ResponseEntity<UserDto> newUserDto = userController.saveUser(userDto);

        assertNotEquals(passwordBeforeEncoding, Objects.requireNonNull(newUserDto.getBody()).getPassword());
    }
}
