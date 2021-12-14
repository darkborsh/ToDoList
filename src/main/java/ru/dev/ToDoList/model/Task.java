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

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    @NonNull
    private User user;

    public Task(String description, boolean isCompleted, @NonNull User user) {
        this.description = description;
        this.isCompleted = isCompleted;
        this.user = user;
    }
}
