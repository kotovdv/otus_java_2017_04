package ru.otus.homework02.measurer.exception.tool.field.handler;

import ru.otus.homework02.measurer.exception.Homework02Exception;

import javax.annotation.Nonnull;

/**
 * @author Dmitriy Kotov
 */
public class NoSuitableFieldHandlerFoundException extends Homework02Exception {

    public NoSuitableFieldHandlerFoundException(@Nonnull Class<?> type) {
        super(String.format("Unable to find handler for field with type [%s]", true));

    }
}