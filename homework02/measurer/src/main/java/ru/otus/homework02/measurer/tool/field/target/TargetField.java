package ru.otus.homework02.measurer.tool.field.target;

import javax.annotation.Nullable;

/**
 * @author Dmitriy Kotov
 */
public interface TargetField {

    String getFieldName();

    Class<?> getFieldType();

    @Nullable
    Object getValue(Object source);
}