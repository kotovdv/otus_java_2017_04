package ru.otus.homework02.measurer.tool.field.handler;

import ru.otus.homework02.measurer.tool.ObjectShallowSizeMeter;
import ru.otus.homework02.measurer.tool.field.FieldVisitor;
import ru.otus.homework02.measurer.tool.field.target.TargetField;
import ru.otus.homework02.measurer.tool.memory.MemorySpecification;
import ru.otus.homework02.measurer.tool.misc.IdentityGenerator;
import ru.otus.homework02.measurer.tool.result.ObjectNodeBuilder;

import javax.annotation.Nonnull;
import java.lang.reflect.Field;

import static java.lang.reflect.Modifier.isStatic;

/**
 * @author Dmitriy Kotov
 */
public abstract class FieldHandler {

    protected final FieldHandlerProvider provider;
    protected final FieldVisitor fieldVisitor;
    protected final IdentityGenerator identityGenerator;
    private final ObjectShallowSizeMeter sizeMeter;
    private final MemorySpecification memorySpecification = MemorySpecification.getCurrentSpecification();

    protected FieldHandler(@Nonnull IdentityGenerator identityGenerator,
                           @Nonnull ObjectShallowSizeMeter sizeMeter,
                           @Nonnull FieldHandlerProvider provider,
                           @Nonnull FieldVisitor fieldVisitor) {
        this.identityGenerator = identityGenerator;
        this.sizeMeter = sizeMeter;
        this.provider = provider;
        this.fieldVisitor = fieldVisitor;
    }

    public abstract boolean canHandle(@Nonnull Class<?> type);

    public abstract ObjectNodeBuilder handleField(final TargetField targetField, final Object source);

    protected boolean isStaticField(Field declaredField) {
        return isStatic(declaredField.getModifiers());
    }

    protected long sizeOf(@Nonnull TargetField targetField, @Nonnull Object source) {
        Object fieldObject = targetField.getValue(source);

        return fieldObject != null
                ? sizeMeter.sizeOf(fieldObject)
                : calculateEmptyReferenceSize();
    }

    private long calculateEmptyReferenceSize() {
        return memorySpecification.getReferenceSize();
    }

}