package ru.dev.ToDoList.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dev.ToDoList.dto.UserDto;
import ru.dev.ToDoList.dto.mappers.UserMapper;
import ru.dev.ToDoList.model.User;
import ru.dev.ToDoList.service.UserService;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping
    public List<UserDto> getUsers() {
        return userService.getUsers();
    }

    @PostMapping
    public ResponseEntity<UserDto> saveUser(@RequestBody @Valid UserDto user){
        userService.save(user);
        return ResponseEntity.created(URI.create("/tasks/" + user.getId())).body(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") @Min(1) long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
