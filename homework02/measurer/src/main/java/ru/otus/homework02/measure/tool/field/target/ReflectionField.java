package ru.otus.homework02.measure.tool.field.target;

import ru.otus.homework02.exception.measure.tool.field.handler.UnableToGetFieldValueException;

import java.lang.reflect.Field;

/**
 * @author Dmitriy Kotov
 */
public class ReflectionField implements TargetField {

    private final Field field;

    public ReflectionField(Field field) {
        this.field = field;
    }

    @Override
    public String getFieldName() {
        return field.getName();
    }

    @Override
    public Class<?> getFieldType() {
        return field.getType();
    }

    @Override
    public Object getValue(Object source) {
        return getFieldValue(field, source);
    }

    private Object getFieldValue(Field field, Object source) {
        Object value;
        try {
            if (field.isAccessible()) {
                value = field.get(source);
            } else {
                field.setAccessible(true);
                value = field.get(source);
                field.setAccessible(false);
            }
        } catch (IllegalAccessException e) {
            throw new UnableToGetFieldValueException(field, source);
        }

        return value;
    }
}