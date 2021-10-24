package service;

import model.TaskList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TaskManager {
    final static String COMMAND_ADD = "add";
    final static String COMMAND_PRINT = "print";
    final static String COMMAND_TOGGLE = "toggle";
    final static String COMMAND_DELETE = "delete";
    final static String COMMAND_EDIT = "edit";
    final static String COMMAND_SEARCH = "search";
    final static String COMMAND_QUIT = "quit";
    final static int EMPTY_ARGUMENT = 1;
    final static int INVALID_ARGUMENT = 2;
    final static int LESS_OR_EQ_ZERO_ARGUMENT = 3;
    final static int OUT_OF_RANGE_ARGUMENT = 4;
    final static int EMPTY_SUBSTRING = 5;

    static TaskList taskList;
    static boolean isWorking;

    private static void help(String commandName, int errNum) {
        switch (errNum) {
            case (EMPTY_ARGUMENT):
                System.out.printf("*Empty argument for the command %s*\n", commandName);
                break;
            case (INVALID_ARGUMENT):
                System.out.printf("*Invalid argument for the command %s*\n", commandName);
                break;
            case (LESS_OR_EQ_ZERO_ARGUMENT):
                System.out.printf("*Argument can't be less than or equal to 0 for the command %s*\n", commandName);
                break;
            case (OUT_OF_RANGE_ARGUMENT):
                System.out.printf("*There is no element with such a number to %s*\n", commandName);
                break;
            case (EMPTY_SUBSTRING):
                System.out.printf("*There is no substring in the tasks you are looking for, in %s*\n", commandName);
                break;
        }
    }

    private static boolean argIsNotEmpty(String commandName, String arg) {
        if (arg.equals("")) {
            help(commandName, EMPTY_ARGUMENT);
            return false;
        }
        return true;
    }

    private static Integer makeNum(String commandName, String arg) {
        try {
            return Integer.parseInt(arg);
        }
        catch (NumberFormatException nfe) {
            help(commandName, INVALID_ARGUMENT);
        }
        return Integer.MIN_VALUE;
    }

    private static boolean isIntArgumentGood(String commandName, int num) {
        if (num == Integer.MIN_VALUE) {
            return false;
        }
        if (num <= 0) {
            help(commandName, LESS_OR_EQ_ZERO_ARGUMENT);
            return false;
        }
        return true;
    }

    private static boolean isIndexCorrect(String commandName, int index) {
        if (index == -1) {
            help(commandName, OUT_OF_RANGE_ARGUMENT);
            return false;
        }
        return true;
    }

    private static void addTask(String arg) {
        if (argIsNotEmpty(COMMAND_ADD, arg)) {
            taskList.add(arg);
        }
    }

    private static void deleteTask(String arg) {
        if (argIsNotEmpty(COMMAND_DELETE, arg)) {
            int num = makeNum(COMMAND_DELETE, arg);
            if (isIntArgumentGood(COMMAND_DELETE, num)) {
                int index = taskList.searchById(num);
                if (isIndexCorrect(COMMAND_DELETE, index)) {
                    taskList.remove(index);
                }
            }
        }
    }

    private static void printTasks(String arg) {
        boolean allPrinted = arg.equals("all");
        if (allPrinted || arg.equals("")) {
            taskList.print(allPrinted);
        } else {
            help(COMMAND_PRINT, INVALID_ARGUMENT);
        }
    }

    private static void toggleTask(String arg) {
        if (argIsNotEmpty(COMMAND_TOGGLE, arg)) {
            int num = makeNum(COMMAND_TOGGLE, arg);
            if (isIntArgumentGood(COMMAND_TOGGLE, num)) {
                int index = taskList.searchById(num);
                if (isIndexCorrect(COMMAND_TOGGLE, index)) {
                    taskList.get(index).toggle();
                }
            }
        }
    }

    private static void editTask(String argWithNum) {
        if (argIsNotEmpty(COMMAND_EDIT, argWithNum)) {
            int num = Integer.parseInt(getKey(argWithNum));
            if (isIntArgumentGood(COMMAND_EDIT, num)) {
                int index = taskList.searchById(num);
                if (isIndexCorrect(COMMAND_EDIT, index)) {
                    String arg = takeRest(argWithNum);
                    if (argIsNotEmpty(COMMAND_EDIT, arg)) {
                        taskList.edit(index, arg);
                    }
                }
            }
        }
    }

    private static void searchTask(String substring) {
        if (argIsNotEmpty(COMMAND_SEARCH, substring)) {
            if(!taskList.searchBySubstring(substring)) {
                help(COMMAND_SEARCH, EMPTY_SUBSTRING);
            }
        }
    }

    private static String getKey(String userInput) {
        int index = userInput.indexOf(' ');
        if (index > -1) {
            return userInput.substring(0, index);
        } else {
            return userInput;
        }
    }

    private static String takeRest(String userInput) {
        int index = userInput.indexOf(' ');
        if (index > -1) {
            return userInput.substring(index + 1);
        } else {
            return "";
        }
    }

    private static void getCommand() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String userInput = reader.readLine();

        String command = getKey(userInput);
        String arg = takeRest(userInput).trim();

        switch (command) {
            case (COMMAND_ADD):
                addTask(arg);
                break;
            case (COMMAND_PRINT):
                printTasks(arg);
                break;
            case (COMMAND_TOGGLE):
                toggleTask(arg);
                break;
            case (COMMAND_DELETE):
                deleteTask(arg);
                break;
            case (COMMAND_EDIT):
                editTask(arg);
                break;
            case (COMMAND_SEARCH):
                searchTask(arg);
                break;
            case (COMMAND_QUIT):
                isWorking = false;
                break;
            default:
                System.out.println("*Invalid command*");
                break;
        }
    }

    public static void work() throws IOException {
        isWorking = true;
        taskList = new TaskList();

        while (isWorking) {
            getCommand();
        }
    }
}