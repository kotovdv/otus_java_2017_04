package ru.otus.homework02.measure.tool.handler;

import ru.otus.homework02.exception.UnableToGetFieldValueException;
import ru.otus.homework02.measure.tool.result.ResultNode;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.lang.reflect.Field;

/**
 * @author Dmitriy Kotov
 */
public abstract class FieldHandler {

    protected final FieldHandlerProvider provider;

    protected FieldHandler(@Nonnull FieldHandlerProvider provider) {
        this.provider = provider;
    }

    public abstract ResultNode handleField(final Field targetField, final Object source);

    @Nullable
    protected Object getFieldValue(Field field, Object source) {
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

    protected Class<?> selectNodeType(Field targetField, Object targetFieldValue) {
        return targetFieldValue != null
                ? targetFieldValue.getClass()
                : targetField.getType();
    }

    public abstract long size(@Nonnull Field currentField, Object source);

    public abstract boolean canHandle(Class<?> type);
}