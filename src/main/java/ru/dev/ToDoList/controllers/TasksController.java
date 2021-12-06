package ru.dev.ToDoList.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dev.ToDoList.dao.TaskDao;
import ru.dev.ToDoList.model.DescriptionHolder;
import ru.dev.ToDoList.model.StatusHolder;
import ru.dev.ToDoList.model.Task;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/tasks")
public class TasksController {
    private final TaskDao taskDao;

    private ResponseEntity<String> actionWithId(long id, Consumer<Task> command) {
        Optional<Task> task = taskDao.get(id);
        if (task.isPresent()) {
            command.accept(task.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public List<Task> getTasks(@RequestParam (name = "substring", required = false) String substring,
                        @RequestParam (name = "isAll", required = false) boolean isAll) {
        return taskDao.getList(isAll, substring);
    }

    @PostMapping
    public ResponseEntity<Task> saveTask(@Valid @RequestBody Task task) {
        taskDao.save(task);
        return ResponseEntity.created(URI.create("/tasks/" + task.getId())).body(task);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable("id") long id) {
        taskDao.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/completed")
    public ResponseEntity<String> statusUpdate(@PathVariable("id") long id, @Valid @RequestBody StatusHolder status) {
        return actionWithId(id, task -> task.setCompleted(status.isCompleted()));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> statusUpdate(@PathVariable("id") long id, @Valid @RequestBody DescriptionHolder desc) {
        return actionWithId(id, task -> task.setDescription(desc.getDescription()));
    }
}
