package ru.dev.ToDoList.contollers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.dev.ToDoList.service.TaskManager;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/task")
public class TasksController {
    private final TaskManager taskManager;

    @GetMapping
}
