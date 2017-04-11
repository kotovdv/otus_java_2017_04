package ru.otus.homework02.measure.tool.field.handler;

import ru.otus.homework02.measure.tool.ObjectShallowSizeMeter;
import ru.otus.homework02.measure.tool.field.FieldVisitor;
import ru.otus.homework02.measure.tool.field.target.TargetField;
import ru.otus.homework02.measure.tool.memory.MemorySpecification;
import ru.otus.homework02.measure.tool.result.ResultNodeBuilder;

import javax.annotation.Nonnull;
import java.lang.reflect.Field;

import static java.lang.reflect.Modifier.isStatic;

/**
 * @author Dmitriy Kotov
 */
public abstract class FieldHandler {

    protected final ObjectShallowSizeMeter sizeMeter;
    protected final FieldHandlerProvider provider;
    protected final FieldVisitor fieldVisitor;
    private final MemorySpecification memorySpecification = MemorySpecification.getCurrentSpecification();

    protected FieldHandler(@Nonnull ObjectShallowSizeMeter sizeMeter,
                           @Nonnull FieldHandlerProvider provider,
                           @Nonnull FieldVisitor fieldVisitor) {
        this.sizeMeter = sizeMeter;
        this.provider = provider;
        this.fieldVisitor = fieldVisitor;
    }

    public abstract boolean canHandle(@Nonnull Class<?> type);

    public abstract ResultNodeBuilder handleField(final TargetField targetField, final Object source);

    protected boolean isStaticField(Field declaredField) {
        return isStatic(declaredField.getModifiers());
    }

    protected long sizeOf(@Nonnull TargetField targetField, @Nonnull Object source) {
        Object fieldObject = targetField.getValue(source);

        return fieldObject != null
                ? sizeMeter.getObjectSize(fieldObject)
                : calculateEmptyReferenceSize();
    }

    private long calculateEmptyReferenceSize() {
        return memorySpecification.getReferenceSize();
    }

}