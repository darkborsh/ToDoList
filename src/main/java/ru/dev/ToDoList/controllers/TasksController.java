package ru.dev.ToDoList.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dev.ToDoList.dao.TaskDao;
import ru.dev.ToDoList.model.DescriptionHolder;
import ru.dev.ToDoList.model.StatusHolder;
import ru.dev.ToDoList.model.Task;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.Min;
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
        try {
            Optional<Task> task = Optional.of(taskDao.getById(id));
            command.accept(task.get());
            taskDao.save(task.get());
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public List<Task> getTasks(@RequestParam (name = "substring", required = false) String substring,
                        @RequestParam (name = "isAll", required = false) boolean isAll) {
        if (substring == null) {
            if (isAll) {
                return taskDao.findAll();
            } else {
                return taskDao.findAllByIsCompleted(false);
            }
        }
        return taskDao.findAllByDescriptionIsLike("%"+substring+"%");
    }

    @PostMapping
    public ResponseEntity<Task> saveTask(@Valid @RequestBody Task task) {
        taskDao.save(task);
        return ResponseEntity.created(URI.create("/tasks/" + task.getId())).body(task);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable("id") @Min(1) long id) {
        taskDao.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Transactional
    @PatchMapping("/{id}/completed")
    public ResponseEntity<String> statusUpdate(@PathVariable("id") @Min(1) long id, @Valid @RequestBody StatusHolder status) {
        return actionWithId(id, task -> task.setCompleted(status.isCompleted()));
    }

    @Transactional
    @PatchMapping("/{id}")
    public ResponseEntity<String> descriptionUpdate(@PathVariable("id") @Min(1) long id, @Valid @RequestBody DescriptionHolder desc) {
        return actionWithId(id, task -> task.setDescription(desc.getDescription()));
    }
}
