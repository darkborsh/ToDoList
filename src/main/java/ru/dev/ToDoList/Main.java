package ru.dev.ToDoList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

import ru.dev.ToDoList.logic.impl.commands.CommandConsumer;
import ru.dev.ToDoList.model.CommandFormat;
import ru.dev.ToDoList.presenters.impl.parser.InputProcessor;

import java.io.BufferedReader;
import java.util.function.Consumer;


@SpringBootApplication
public class Main implements CommandLineRunner {
    final BufferedReader reader;
    final InputProcessor inputProcessor;
    final Consumer<CommandFormat> consumer;

    @Autowired
    public Main(BufferedReader reader, InputProcessor inputProcessor, CommandConsumer consumer) {
        this.reader = reader;
        this.inputProcessor = inputProcessor;
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
