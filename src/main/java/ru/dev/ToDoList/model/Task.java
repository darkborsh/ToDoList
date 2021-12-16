package ru.dev.ToDoList.model;

import lombok.*;

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

    @ManyToOne
    @JoinColumn(name = "user_id")
    @NonNull
    private User user;
}
