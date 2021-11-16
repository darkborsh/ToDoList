package logic;

import model.Task;

import java.util.stream.Stream;

public interface TaskPrinter {
    void print(Stream<Task> tasks);
}
