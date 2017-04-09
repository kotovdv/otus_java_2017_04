package ru.otus.homework02.measure.tool.handler.concrete;

import com.google.common.collect.ImmutableMap;
import ru.otus.homework02.measure.tool.handler.FieldHandler;
import ru.otus.homework02.measure.tool.handler.FieldHandlerProvider;
import ru.otus.homework02.measure.tool.result.ResultNode;

import javax.annotation.Nonnull;
import java.lang.reflect.Field;
import java.util.Collections;
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


    public PrimitiveTypeHandler(@Nonnull FieldHandlerProvider provider) {
        super(provider);
    }

    @Override
    public ResultNode handleField(Field targetField, Object source) {
        return new ResultNode(targetField,
                size(targetField, source),
                0,
                Collections.emptyList());
    }

    @Override
    public boolean canHandle(Class<?> type) {
        return primitiveSizes.keySet().contains(type);
    }

    @Override
    public long size(@Nonnull Field targetField, Object source) {
        Class<?> type = targetField.getType();

        return primitiveSizes.get(type);
    }
}
