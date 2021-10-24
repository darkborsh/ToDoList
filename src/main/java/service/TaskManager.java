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
    final static String COMMAND_QUIT = "quit";

    static TaskList taskList;
    static boolean isWorking;

    private static void addTask(String arg) {
        if (arg.equals("")) {
            System.out.println("*Description of new task is empty*");
        } else {
            taskList.add(arg);
        }
    }

    private static void deleteTask(String rest) {
        if (rest.equals("")) {
            System.out.println("*Empty argument for the command delete*");
        } else {
            try {
                int num = Integer.parseInt(rest);
                int index = taskList.searchById(num);
                if (index == -1) {
                    System.out.println("*There is no element with such a number to delete*");
                } else {
                    taskList.remove(index);
                }
            }
            catch (NumberFormatException nfe) {
                System.out.println("*Invalid arguments for the command delete*");
            }
        }
    }

    private static void printTasks(String rest) {
        boolean allPrinted = rest.equals("all");
        if (allPrinted || rest.equals("")) {
            taskList.print(allPrinted);
        } else {
            System.out.println("*Invalid arguments for the command print*");
        }
    }

    private static void toggleTask(String rest) {
        if (rest.equals("")) {
            System.out.println("*Invalid arguments for the command toggle*");
        } else {
            try {
                int num = Integer.parseInt(rest);
                int index = taskList.searchById(num);
                if (index == -1) {
                    System.out.println("*There is no element with such a number to toggle*");
                } else {
                    taskList.get(index).toggle();
                }
            }
            catch (NumberFormatException nfe) {
                System.out.println("*Invalid arguments for the command toggle*");
            }
        }
    }

    private static void editTask(String restWithNum) {
        if (restWithNum.equals("")) {
            System.out.println("*Invalid arguments for the command edit*");
        } else {
            try {
                int num = Integer.parseInt(getKey(restWithNum));
                int index = taskList.searchById(num);
                if (index == -1) {
                    System.out.println("*There is no element with such a number to edit*");
                } else {
                    String rest = takeRest(restWithNum);
                    if (rest.equals("")) {
                        System.out.println("*New description in command edit is empty*");
                    } else {
                        taskList.edit(index, rest);
                    }
                }
            }
            catch (NumberFormatException nfe) {
                System.out.println("*Invalid arguments for the command edit*");
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