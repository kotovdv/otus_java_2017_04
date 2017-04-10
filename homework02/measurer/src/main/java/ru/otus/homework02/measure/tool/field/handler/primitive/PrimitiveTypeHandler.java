package ru.otus.homework02.measure.tool.field.handler.primitive;

import com.google.common.collect.ImmutableMap;
import ru.otus.homework02.measure.tool.ShallowObjectSizeMeter;
import ru.otus.homework02.measure.tool.field.FieldVisitor;
import ru.otus.homework02.measure.tool.field.handler.FieldHandler;
import ru.otus.homework02.measure.tool.field.handler.FieldHandlerProvider;
import ru.otus.homework02.measure.tool.result.ResultNodeBuilder;

import javax.annotation.Nonnull;
import java.lang.reflect.Field;
import java.util.Map;

/**
 * @author Dmitriy Kotov
 */
public class PrimitiveTypeHandler extends FieldHandler {

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


    public PrimitiveTypeHandler(@Nonnull ShallowObjectSizeMeter sizeMeter,
                                @Nonnull FieldHandlerProvider provider,
                                @Nonnull FieldVisitor fieldVisitor) {
        super(sizeMeter, provider, fieldVisitor);
    }

    @Override
    public ResultNodeBuilder handleField(Field targetField, Object source) {
        Object targetFieldValue = getFieldValue(targetField, source);

        return new ResultNodeBuilder()
                .fieldName(targetField.getName())
                .fieldType(targetField.getType())
                .value(targetFieldValue)
                .personalSize(sizeOf(targetField))
                .branchSize(0);
    }

    @Override
    public boolean canHandle(Class<?> type) {
        return primitiveSizes.keySet().contains(type);
    }

    private long sizeOf(@Nonnull Field targetField) {
        Class<?> type = targetField.getType();

        return primitiveSizes.get(type);
    }
}