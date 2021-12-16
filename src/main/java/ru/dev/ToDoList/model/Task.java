package ru.dev.ToDoList.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Table(name = "tasks")
@Entity
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
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @NonNull
    private User user;
}
