package ru.otus.homework02.measurer.tool.field.handler.primitive;

import com.google.common.collect.ImmutableMap;
import ru.otus.homework02.measurer.tool.ObjectShallowSizeMeter;
import ru.otus.homework02.measurer.tool.field.FieldVisitor;
import ru.otus.homework02.measurer.tool.field.handler.FieldHandler;
import ru.otus.homework02.measurer.tool.field.handler.FieldHandlerProvider;
import ru.otus.homework02.measurer.tool.field.target.TargetField;
import ru.otus.homework02.measurer.tool.result.ObjectNodeBuilder;

import javax.annotation.Nonnull;
import java.util.Map;

/**
 * @author Dmitriy Kotov
 */
public final class PrimitiveTypeHandler extends FieldHandler {

    private Map<Class<?>, Long> primitiveSizes = ImmutableMap.<Class<?>, Long>builder()
            .put(Byte.TYPE, 1L)
            .put(Boolean.TYPE, 1L)
            .put(Short.TYPE, 2L)
            .put(Character.TYPE, 2L)
            .put(Integer.TYPE, 4L)
            .put(Float.TYPE, 4L)
            .put(Long.TYPE, 8L)
            .put(Double.TYPE, 8L)
            .build();


    public PrimitiveTypeHandler(@Nonnull ObjectShallowSizeMeter sizeMeter,
                                @Nonnull FieldHandlerProvider provider,
                                @Nonnull FieldVisitor fieldVisitor) {
        super(sizeMeter, provider, fieldVisitor);
    }

    @Override
    public ObjectNodeBuilder handleField(TargetField targetField, Object source) {
        Object targetFieldValue = targetField.getValue(source);

        return new ObjectNodeBuilder()
                .fieldName(targetField.getFieldName())
                .fieldType(targetField.getFieldType())
                .instanceType(targetField.getFieldType())
                .value(targetFieldValue)
                .personalSize(sizeOf(targetField))
                .branchSize(0);
    }

    @Override
    public boolean canHandle(@Nonnull Class<?> type) {
        return primitiveSizes.keySet().contains(type);
    }

    private long sizeOf(@Nonnull TargetField targetField) {
        Class<?> type = targetField.getFieldType();

        return primitiveSizes.get(type);
    }
}
