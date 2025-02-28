package br.com.marcoscapella.events.exception;

public class UserIndicatorNotFoundException extends RuntimeException {
    public UserIndicatorNotFoundException(String message) {
        super(message);
    }
}
