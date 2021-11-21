package ru.dev.ToDoList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

import ru.dev.ToDoList.logic.TaskDao;
import ru.dev.ToDoList.logic.impl.commands.CommandConsumer;
import ru.dev.ToDoList.logic.impl.commands.StaticCommandFactory;
import ru.dev.ToDoList.model.CommandFormat;
import ru.dev.ToDoList.presenters.ErrorHandler;
import ru.dev.ToDoList.presenters.impl.parser.InputProcessor;

import java.io.BufferedReader;
import java.util.function.Consumer;


@SpringBootApplication
public class Main implements CommandLineRunner {
    final BufferedReader reader;
    final InputProcessor inputProcessor;
    final ErrorHandler errorHandler;
    final TaskDao taskDao;
    final StaticCommandFactory commandFactory;
    final Consumer<CommandFormat> consumer;

    @Autowired
    public Main(BufferedReader reader, InputProcessor inputProcessor, ErrorHandler errorHandler, TaskDao taskDao,
                StaticCommandFactory commandFactory, CommandConsumer consumer) {
        this.reader = reader;
        this.inputProcessor = inputProcessor;
        this.errorHandler = errorHandler;
        this.taskDao = taskDao;
        this.commandFactory = commandFactory;
        this.consumer = consumer;
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) {
        reader.lines()
                .map(inputProcessor)
                .forEach(consumer);
    }
}
