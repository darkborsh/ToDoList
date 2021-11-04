package service;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class InputProcessor {
    private static String commandName;
    private static String commandArg;
    private static boolean isProcessing;

    private static void splitInput(String userInput) {
        int index = userInput.indexOf(' ');
        if (index > -1) {
            commandName = userInput.substring(0, index);
            commandArg = userInput.substring(index + 1);
        } else {
            commandName = userInput;
        }
    }

    private static void getInput() throws IOException  {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String userInput = reader.readLine();
        splitInput(userInput);
    }

    public static void process() throws IOException {
        isProcessing = true;
        commandName = "";
        commandArg = "";
        //CommandProcessor processor = new CommandProcessor();

        while(isProcessing) {
            getInput();
            //isProcessing = processor.process(commandName, commandArg);
        }
    }
}