package ru.otus.homework02.measurer.exception.tool.field;

import ru.otus.homework02.measurer.exception.Homework02Exception;

/**
 * @author Dmitriy Kotov
 */
public class FieldWasPreviouslyVisitedException extends Homework02Exception {

    public FieldWasPreviouslyVisitedException(String fieldName, Class<?> fieldType) {
        super(String.format("Failed to visit field, since it was previously visited. Name = [%s]. Type = [%s]",
                fieldName,
                fieldType.getSimpleName()));
    }
}