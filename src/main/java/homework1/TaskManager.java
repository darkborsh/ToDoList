package homework1;

import java.util.Scanner;

class TaskManager {
    static TaskList tasklist;
    static boolean is_working;

    public static void add_task(String command) {
        String rest = take_rest(command);
        if (!rest.equals("")) {
            tasklist.add(rest);
        }
        else {
            System.out.println("*Description of new task is empty*");
        }
    }

    public static void print_tasks(String command) {
        String rest = take_rest(command);
        if (rest.equals("all")) {
            tasklist.print();
        }
        else if (rest.equals("")) {
            tasklist.print_unchecked();
        }
        else {
            System.out.println("*Invalid arguments for the command print*");
        }
    }

    public static void toggle_task(String command) {
        String rest = take_rest(command);
        if (rest.equals("")) {
            System.out.println("*Invalid arguments for the command toggle*");
        }
        else {
            try {
                int num = Integer.parseInt(rest);
                if (num > tasklist.size() - 1 || num < tasklist.size() - 1)
                    System.out.println("*There is no element with such a number to toggle*");
                else {
                    tasklist.get(num).toggle();
                }
            }
            catch (NumberFormatException nfe) {
                System.out.println("NumberFormatException: " + nfe.getMessage());
                System.out.println("*Invalid arguments for the command toggle*");
            }
        }
    }

    private static String get_key(String command) {
        int index = command.indexOf(' ');
        if (index > -1) {
            return command.substring(0, index);
        }
        else {
            return command;
        }
    }

    private static String take_rest(String command) {
        int index = command.indexOf(' ');
        if (index > -1) {
            return command.substring(index + 1);
        }
        else {
            return "";
        }
    }

    private static void show_help() {
        System.out.println("All commands:\nadd <task description>\nprint [all] (print or print all)\n" +
                "toggle <id>\nquit"
        );
    }

    private static void get_command() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("command:");

        String command = scanner.nextLine();

        String key = get_key(command);

        switch (key) {
            case ("add"):
                add_task(command);
                break;
            case ("print"):
                print_tasks(command);
                break;
            case ("toggle"):
                toggle_task(command);
                break;
            case ("quit"):
                is_working = false;
                break;
            case ("help"):
                show_help();
                break;
            default:
                System.out.println("*Invalid command*");
                break;
        }
    }

    public static void work() {
        is_working = true;
        tasklist = new TaskList();

        System.out.println("Welcome to ToDoList!\nPrint your commands below or write \"help\" to get command list");
        while (is_working) {
            get_command();
        }
    }
}