package service;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import model.Task;

import java.util.Map;

public class InputProcessor {
    private static String commandName;
    private static String commandArg;
    private static boolean isProcessing;

    private static void splitInput(String userInput) {
        int index = userInput.indexOf(' ');
        if (index > -1) {
            commandName = userInput.substring(0, index);
            commandArg = userInput.substring(index + 1).trim();
        } else {
            commandName = userInput;
            commandArg = "";
        }
    }

    private static void getInput() throws IOException  {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String userInput = reader.readLine();
        splitInput(userInput);
    }

    public static void process(Map<String, Task> data) throws IOException {
        isProcessing = true;
        commandName = "";
        commandArg = "";
        CommandProcessor processor = new CommandProcessor();

        while(isProcessing) {
            getInput();
            isProcessing = processor.process(commandName, commandArg, data);
        }
    }
}