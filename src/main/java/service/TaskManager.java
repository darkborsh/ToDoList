package service;

import model.TaskList;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.function.Predicate;

public class TaskManager {
    final static String COMMAND_ADD = "add";
    final static String COMMAND_PRINT = "print";
    final static String COMMAND_TOGGLE = "toggle";
    final static String COMMAND_DELETE = "delete";
    final static String COMMAND_EDIT = "edit";
    final static String COMMAND_SEARCH = "search";
    final static String COMMAND_QUIT = "quit";

    enum ErrTypes {
        EMPTY_ARGUMENT,
        INVALID_ARGUMENT
    }

    static TaskList taskList;
    static boolean isWorking;

    private static void help(String commandName, ErrTypes errType) {
        switch (errType) {
            case EMPTY_ARGUMENT:
                System.out.printf("*Empty argument for the command %s*\n", commandName);
                break;
            case INVALID_ARGUMENT:
                System.out.printf("*Invalid argument for the command %s*\n", commandName);
                break;
        }
    }

    private static boolean argIsNotEmpty(String commandName, String arg) {
        if (arg.equals("")) {
            help(commandName, ErrTypes.EMPTY_ARGUMENT);
            return false;
        }
        return true;
    }

    private static void addTask(String arg) {
        if (argIsNotEmpty(COMMAND_ADD, arg)) {
            taskList.add(arg);
        }
    }

    private static void printTasks(String arg) {
        boolean allPrinted = arg.equals("all");
        if (allPrinted || arg.equals("")) {
            taskList.print(allPrinted);
        } else {
            help(COMMAND_PRINT, ErrTypes.INVALID_ARGUMENT);
        }
    }

    private static void searchTask(String arg) {
        if (argIsNotEmpty(COMMAND_SEARCH, arg)) {
            taskList.search(arg);
        }
    }

    private static void simpleAction(String commandName, String arg, Predicate<String> action) {
        if (argIsNotEmpty(commandName, arg)) {
            if (!action.test(arg)) {
                help(commandName, ErrTypes.INVALID_ARGUMENT);
            }
        }
    }

    private static void toggleTask(String arg) {
        simpleAction(COMMAND_TOGGLE, arg, a -> taskList.toggle(arg));
    }

    private static void deleteTask(String arg) {
        simpleAction(COMMAND_DELETE, arg, a -> taskList.delete(arg));
    }

    private static void editTask(String argWithNum) {
        if (argIsNotEmpty(COMMAND_EDIT, argWithNum)) {
            String arg = takeRest(argWithNum);
            if (argIsNotEmpty(COMMAND_EDIT, arg)) {
                if (!taskList.edit(getKey(argWithNum), arg)) {
                    help(COMMAND_EDIT, ErrTypes.INVALID_ARGUMENT);
                }
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