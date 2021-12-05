package ru.dev.ToDoList.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class DescriptionHolder {
    @NotBlank
    private String description;
}
