package model;

import logic.TaskDao;
import lombok.Data;
import parser.CommandFormat;

import java.util.function.BiConsumer;
import java.util.function.Predicate;

@Data
public class Command {
    Predicate<CommandFormat> validator;
    BiConsumer<CommandFormat, TaskDao> executor;
}
