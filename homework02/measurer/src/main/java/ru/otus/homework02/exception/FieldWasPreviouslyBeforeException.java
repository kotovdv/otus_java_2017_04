package ru.otus.homework02.exception;

/**
 * @author Dmitriy Kotov
 */
public class FieldWasPreviouslyBeforeException extends Homework02Exception {

    public FieldWasPreviouslyBeforeException(String fieldName, Class<?> fieldType) {
        super(String.format("Failed to visit field, since it was previously visited. Name = [%s]. Type = [%s]",
                fieldName,
                fieldType.getSimpleName()));
    }
}