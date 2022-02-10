package ru.dev.ToDoList.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dev.ToDoList.dto.UserDto;
import ru.dev.ToDoList.service.UserService;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @Transactional
    @GetMapping
    public List<UserDto> getUsers() {
        return userService.getUsers();
    }

    @PostMapping
    public ResponseEntity<UserDto> saveUser(@Valid @RequestBody UserDto userDto){
        UserDto u = userService.save(userDto);
        u.setPassword("<hidden>");
        return ResponseEntity.created(URI.create("/users/" + u.getId())).body(u);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") @Min(1) long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
