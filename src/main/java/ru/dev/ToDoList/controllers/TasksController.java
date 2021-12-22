package ru.dev.ToDoList.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dev.ToDoList.dto.mappers.TaskMapper;
import ru.dev.ToDoList.model.DescriptionHolder;
import ru.dev.ToDoList.model.StatusHolder;
import ru.dev.ToDoList.model.Task;
import ru.dev.ToDoList.service.TaskService;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/tasks")
public class TasksController {
    private final TaskService taskService;
    private final TaskMapper taskMapper;

    @GetMapping
    public List<Task> getTasks(@RequestParam (name = "substring", required = false) String substring,
                        @RequestParam (name = "isAll", required = false) boolean isAll) {
        return taskMapper.toTaskList(taskService.getAll(substring, isAll));
    }

    @PostMapping
    public ResponseEntity<Task> saveTask(@Valid @RequestBody Task task) {
        Task t = taskService.save(taskMapper.toDto(task));
        return ResponseEntity.created(URI.create("/tasks/" + t.getId())).body(t);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable("id") @Min(1) long id) {
        taskService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Transactional
    @PatchMapping("/{id}/completed")
    public ResponseEntity<String> statusUpdate(@PathVariable("id") @Min(1) long id,
                                               @Valid @RequestBody StatusHolder status) {
        if (taskService.updateStatus(id, status.isCompleted())) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @Transactional
    @PatchMapping("/{id}")
    public ResponseEntity<String> descriptionUpdate(@PathVariable("id") @Min(1) long id,
                                                    @Valid @RequestBody DescriptionHolder desc) {
        if (taskService.updateDescription(id, desc.getDescription())) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
