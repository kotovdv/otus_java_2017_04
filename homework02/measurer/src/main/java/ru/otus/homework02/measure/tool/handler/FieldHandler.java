package ru.otus.homework02.measure.tool.handler;

import ru.otus.homework02.exception.UnableToGetFieldValueException;
import ru.otus.homework02.measure.tool.result.ResultNodeBuilder;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.lang.reflect.Field;

import static java.lang.reflect.Modifier.isStatic;

/**
 * @author Dmitriy Kotov
 */
public abstract class FieldHandler {

    protected final FieldHandlerProvider provider;
    protected final FieldVisitor fieldVisitor;

    protected FieldHandler(@Nonnull FieldHandlerProvider provider, FieldVisitor fieldVisitor) {
        this.provider = provider;
        this.fieldVisitor = fieldVisitor;
    }

    public abstract boolean canHandle(Class<?> type);

    public abstract long size(@Nonnull Field currentField, Object source);

    public abstract ResultNodeBuilder handleField(final Field targetField, final Object source);

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

    protected boolean isStaticField(Field declaredField) {
        return isStatic(declaredField.getModifiers());
    }
}