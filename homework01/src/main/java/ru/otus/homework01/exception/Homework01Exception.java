package ru.otus.homework01.exception;

/**
 * @author Dmitriy Kotov
 */
public class Homework01Exception extends RuntimeException {

    public Homework01Exception() {
    }

    public Homework01Exception(String message) {
        super(message);
    }

    public Homework01Exception(Throwable cause) {
        super(cause);
    }

    public Homework01Exception(String message, Throwable cause) {
        super(message, cause);
    }
}