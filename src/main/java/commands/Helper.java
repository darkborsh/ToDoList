package commands;

public class Helper {
    public static void help(String commandName, ErrTypes errType) {
        switch (errType) {
            case EMPTY_ARGUMENT:
                System.out.printf("*Empty argument for the command %s*\n", commandName);
                break;
            case INVALID_ARGUMENT:
                System.out.printf("*Invalid argument for the command %s*\n", commandName);
                break;
        }
    }
}