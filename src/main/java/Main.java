import java.io.IOException;
import java.util.Map;
import java.util.LinkedHashMap;

import model.Task;
import service.InputProcessor;

public class Main {
    public static void main(String[] args) throws IOException {
        Map<String, Task> data = new LinkedHashMap<>();
        InputProcessor.process(data);
    }
}