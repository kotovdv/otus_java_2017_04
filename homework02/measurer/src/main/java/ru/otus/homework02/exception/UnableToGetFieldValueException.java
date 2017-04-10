package ru.otus.homework02.exception;

import java.lang.reflect.Field;

/**
 * @author Dmitriy Kotov
 */
public class UnableToGetFieldValueException extends Homework02Exception {

    public UnableToGetFieldValueException(Field field, Object instance) {
        super("Unable to get value of [" + field + "] from instance [" + instance + "]");
    }
}