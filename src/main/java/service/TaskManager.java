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
    final static String COMMAND_QUIT = "quit";

    static TaskList taskList;
    static boolean isWorking;

    private static void addTask(String command) {
        String rest = takeRest(command).trim();
        if (rest.equals("")) {
            System.out.println("*Description of new task is empty*");
        } else {
            taskList.add(rest);
        }
    }

    private static void deleteTask(String command) {
        String rest = takeRest(command).trim();
        if (rest.equals("")) {
            System.out.println("*Invalid arguments for the command delete*");
        } else {
            try {
                int num = Integer.parseInt(rest);
                if (num <= 0 || num > taskList.size()) {
                    System.out.println("*There is no element with such a number to delete*");
                } else {
                    taskList.remove(num - 1);
                }
            }
            catch (NumberFormatException nfe) {
                System.out.println("*Invalid arguments for the command delete*");
            }
        }
    }

    private static void printTasks(String command) {
        String rest = takeRest(command);
        boolean allPrinted = rest.equals("all");
        if (allPrinted || rest.equals("")) {
            taskList.print(allPrinted);
        } else {
            System.out.println("*Invalid arguments for the command print*");
        }
    }

    private static void toggleTask(String command) {
        String rest = takeRest(command).trim();
        if (rest.equals("")) {
            System.out.println("*Invalid arguments for the command toggle*");
        } else {
            try {
                int num = Integer.parseInt(rest);
                if (num <= 0 || num > taskList.size()) {
                    System.out.println("*There is no element with such a number to toggle*");
                } else {
                    taskList.get(num - 1).toggle();
                }
            }
            catch (NumberFormatException nfe) {
                System.out.println("*Invalid arguments for the command toggle*");
            }
        }
    }

    private static String getKey(String command) {
        int index = command.indexOf(' ');
        if (index > -1) {
            return command.substring(0, index);
        } else {
            return command;
        }
    }

    private static String takeRest(String command) {
        int index = command.indexOf(' ');
        if (index > -1) {
            return command.substring(index + 1);
        } else {
            return "";
        }
    }

    private static void getCommand() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String command = reader.readLine();

        String key = getKey(command);

        switch (key) {
            case (COMMAND_ADD):
                addTask(command);
                break;
            case (COMMAND_PRINT):
                printTasks(command);
                break;
            case (COMMAND_TOGGLE):
                toggleTask(command);
                break;
            case (COMMAND_DELETE):
                deleteTask(command);
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