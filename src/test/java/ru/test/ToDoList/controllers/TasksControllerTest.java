package ru.test.ToDoList.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.dev.ToDoList.controllers.TasksController;
import ru.dev.ToDoList.dto.TaskDto;
import ru.dev.ToDoList.model.DescriptionHolder;
import ru.dev.ToDoList.model.StatusHolder;
import ru.dev.ToDoList.service.TaskService;
import static org.junit.jupiter.api.Assertions.*;

import java.net.URI;
import java.util.List;

import static org.mockito.Mockito.*;

public class TasksControllerTest {
    private TaskService taskService;
    private TasksController tasksController;

    @BeforeEach
    public void setup() {
        taskService = mock(TaskService.class);
        tasksController = new TasksController(taskService);
    }

    @Test
    public void getTasks_DoNotReturnNull_Always() {
        final List<TaskDto> tasksListWithNullSubstring = tasksController.getTasks(null, false);
        final List<TaskDto> tasksListWithNotNullSubstring = tasksController.getTasks("anyString", true);

        assertNotNull(tasksListWithNullSubstring);
        assertNotNull(tasksListWithNotNullSubstring);
    }

    @Test
    public void getTasks_ReturnsCorrectTaskList_Always() {
        TaskDto task = new TaskDto();
        final String username = "Ivan";
        task.setUser(username);
        task.setId(1);
        task.setDescription("do something");
        final List<TaskDto> expectedList = List.of(task);

        when(taskService.getAll(anyString(), anyBoolean())).thenReturn(expectedList);

        List<TaskDto> taskListFromMethod = tasksController.getTasks("do something", false);

        assertEquals(expectedList, taskListFromMethod);
        verify(taskService).getAll("do something", false);
        verifyNoMoreInteractions(taskService);
    }

    @Test
    public void saveTask_SaveTaskAndReturnsLocation_Always() {
        DescriptionHolder newTaskDescription = new DescriptionHolder();
        newTaskDescription.setDescription("anyString");
        TaskDto task = new TaskDto();
        task.setDescription(newTaskDescription.getDescription());
        when(taskService.save(newTaskDescription)).thenReturn(task);

        ResponseEntity<TaskDto> response = tasksController.saveTask(newTaskDescription);
        URI actualLocation = response.getHeaders().getLocation();

        assertEquals(task, response.getBody());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(actualLocation);
        assertEquals("/tasks/" + task.getId(), actualLocation.toString());

        verify(taskService).save(newTaskDescription);
        verifyNoMoreInteractions(taskService);
    }

    @Test
    public void deleteTask_DeleteTask_Always() {
        tasksController.deleteTask(anyLong());

        verify(taskService).delete(anyLong());
        verifyNoMoreInteractions(taskService);
    }

    @Test
    public void statusUpdate_ThrowsNotFound_IfTaskNotFound() {
        when(taskService.updateStatus(anyLong(), anyBoolean())).thenReturn(false);

        ResponseEntity<String> response = tasksController.statusUpdate(1, new StatusHolder());

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void statusUpdate_ChangeTaskStatus_IfTaskFound() {
        when(taskService.updateStatus(anyLong(), anyBoolean())).thenReturn(true);

        ResponseEntity<String> response = tasksController.statusUpdate(1, new StatusHolder());

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void descriptionUpdate_ChangeTaskDescription_IfTaskFound() {
        when(taskService.updateDescription(anyLong(), anyString())).thenReturn(true);
        DescriptionHolder desc = new DescriptionHolder();
        desc.setDescription("something");

        ResponseEntity<String> response = tasksController.descriptionUpdate(1, desc);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void descriptionUpdate_ThrowsNotFound_IfTaskNotFound() {
        when(taskService.updateDescription(anyLong(), anyString())).thenReturn(false);

        ResponseEntity<String> response = tasksController.descriptionUpdate(1, new DescriptionHolder());

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
