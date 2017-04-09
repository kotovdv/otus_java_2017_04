package ru.otus.homework02.exception;

/**
 * @author Dmitriy Kotov
 */
public class TargetFieldNotFoundException extends Homework02Exception {

    public TargetFieldNotFoundException() {
        super("Unable to find target field");
    }
}