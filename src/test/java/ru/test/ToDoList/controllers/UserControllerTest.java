package ru.test.ToDoList.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.dev.ToDoList.controllers.UserController;
import ru.dev.ToDoList.dto.UserDto;
import ru.dev.ToDoList.model.Role;
import ru.dev.ToDoList.service.UserService;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserControllerTest {
    private UserController userController;
    private UserService userService;
    private UserDto user;
    private final String userPassword = "4765";

    private UserDto userCreate() {
        UserDto userDto = new UserDto();
        userDto.setName("Ivan");
        userDto.setRole(Role.USER);
        userDto.setPassword(userPassword);
        return userDto;
    }

    @BeforeEach
    public void setup() {
        user = userCreate();
        userService = mock(UserService.class);
        userController = new UserController(userService);
        when(userService.save(user)).thenReturn(user);
    }

    @Test
    public void getUsers_ReturnsCorrectUserListAndDoNotReturnNull_Always() {
        final List<UserDto> expectedUserList = new ArrayList<>();
        expectedUserList.add(user);
        when(userService.getUsers()).thenReturn(expectedUserList);

        final List<UserDto> actualUserList = userController.getUsers();

        assertEquals(expectedUserList, actualUserList);
        assertNotNull(actualUserList);
    }

    @Test
    public void saveUser_SaveUserAndErasePasswordAndReturnsCorrectLocation_Always() {
        final ResponseEntity<UserDto> response = userController.saveUser(user);
        final URI actualLocation = response.getHeaders().getLocation();

        verify(userService).save(any(UserDto.class));
        verifyNoMoreInteractions(userService);

        assertNotEquals(userPassword, Objects.requireNonNull(response.getBody()).getPassword());

        assertEquals(user, response.getBody());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(actualLocation);
        assertEquals("/users/" + user.getId(), actualLocation.toString());
    }

    @Test
    public void deleteUser_DeleteUser_Always() {
        userController.deleteUser(user.getId());

        verify(userService).delete(user.getId());
        verifyNoMoreInteractions(userService);
    }
}
