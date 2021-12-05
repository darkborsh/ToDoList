package ru.dev.ToDoList.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity(name = "tasks")
@NoArgsConstructor
@Data
public class Task {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    private boolean isCompleted;
    @NotBlank
    private String description;
}