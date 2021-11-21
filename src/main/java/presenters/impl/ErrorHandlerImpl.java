package presenters.impl;

import presenters.ErrorHandler;

public class ErrorHandlerImpl implements ErrorHandler {
    @Override
    public void handle(String error) {
        System.out.println(error);
    }
}
