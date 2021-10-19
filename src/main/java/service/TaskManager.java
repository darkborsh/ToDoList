package service;

import model.TaskList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class TaskManager {
    final static String COMMAND_ADD = "add";
    final static String COMMAND_PRINT = "print";
    final static String COMMAND_TOGGLE = "toggle";
    final static String COMMAND_QUIT = "quit";


    static TaskList tasklist;
    static boolean isWorking;

    public static void addTask(String command) {
        String rest = takeRest(command).trim();
        if (rest.contains("\n")) {
            System.out.println("*Description of new task has \\n symbol*");
        } else if (rest.equals("")) {
            System.out.println("*Description of new task is empty*");
        } else {
            tasklist.add(rest);
        }
    }

    public static void printTasks(String command) {
        String rest = takeRest(command);
        if (rest.equals("all")) {
            tasklist.print();
        } else if (rest.equals("")) {
            tasklist.printIncomplete();
        } else {
            System.out.println("*Invalid arguments for the command print*");
        }
    }

    public static void toggleTask(String command) {
        String rest = takeRest(command).trim();
        if (rest.equals("")) {
            System.out.println("*Invalid arguments for the command toggle*");
        } else {
            try {
                int num = Integer.parseInt(rest);
                if (num <= 0 || num > tasklist.size()) {
                    System.out.println("*There is no element with such a number to toggle*");
                } else {
                    tasklist.get(num - 1).toggle();
                }
            }
            catch (NumberFormatException nfe) {
                System.out.println("NumberFormatException: " + nfe.getMessage());
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

        System.out.print("command:");

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
        tasklist = new TaskList();

        System.out.println("Welcome to ToDoList!\nPrint your commands below");
        while (isWorking) {
            getCommand();
        }
    }
}