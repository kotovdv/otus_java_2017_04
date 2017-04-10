package ru.otus.homework02.measure.tool.field.handler;

import ru.otus.homework02.exception.measure.tool.field.handler.UnableToGetFieldValueException;
import ru.otus.homework02.measure.tool.ShallowObjectSizeMeter;
import ru.otus.homework02.measure.tool.field.FieldVisitor;
import ru.otus.homework02.measure.tool.result.ResultNodeBuilder;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.lang.reflect.Field;

import static java.lang.reflect.Modifier.isStatic;

/**
 * @author Dmitriy Kotov
 */
public abstract class FieldHandler {

    protected final ShallowObjectSizeMeter sizeMeter;
    protected final FieldHandlerProvider provider;
    protected final FieldVisitor fieldVisitor;

    protected FieldHandler(@Nonnull ShallowObjectSizeMeter sizeMeter,
                           @Nonnull FieldHandlerProvider provider,
                           @Nonnull FieldVisitor fieldVisitor) {
        this.sizeMeter = sizeMeter;
        this.provider = provider;
        this.fieldVisitor = fieldVisitor;
    }

    public abstract boolean canHandle(Class<?> type);

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