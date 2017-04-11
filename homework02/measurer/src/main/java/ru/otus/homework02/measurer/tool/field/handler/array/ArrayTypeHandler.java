package ru.otus.homework02.measurer.tool.field.handler.array;

import com.google.common.collect.ImmutableMap;
import ru.otus.homework02.measurer.tool.ObjectShallowSizeMeter;
import ru.otus.homework02.measurer.tool.field.FieldVisitor;
import ru.otus.homework02.measurer.tool.field.handler.FieldHandler;
import ru.otus.homework02.measurer.tool.field.handler.FieldHandlerProvider;
import ru.otus.homework02.measurer.tool.field.handler.reference.ReferenceTypeHandler;
import ru.otus.homework02.measurer.tool.field.handler.reference.TargetObjectHandlingResult;
import ru.otus.homework02.measurer.tool.field.target.ArrayField;
import ru.otus.homework02.measurer.tool.field.target.TargetField;
import ru.otus.homework02.measurer.tool.result.ResultNodeBuilder;

import javax.annotation.Nonnull;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Dmitriy Kotov
 */
public final class ArrayTypeHandler extends ReferenceTypeHandler {

    private Map<Class<?>, Class<?>> primitiveArrays = ImmutableMap.<Class<?>, Class<?>>builder()
            .put(byte[].class, byte.class)
            .put(boolean[].class, boolean.class)
            .put(short[].class, short.class)
            .put(char[].class, char.class)
            .put(int[].class, int.class)
            .put(float[].class, float.class)
            .put(long[].class, long.class)
            .put(double[].class, double.class)
            .build();


    public ArrayTypeHandler(@Nonnull ObjectShallowSizeMeter sizeMeter,
                            @Nonnull FieldHandlerProvider provider,
                            @Nonnull FieldVisitor fieldVisitor) {
        super(sizeMeter, provider, fieldVisitor);
    }

    @Override
    public boolean canHandle(@Nonnull Class<?> type) {
        return type.isArray();
    }

    @Override
    protected TargetObjectHandlingResult handleTargetObject(@Nonnull Object targetArray) {
        List<ResultNodeBuilder> children = new ArrayList<>();

        long branchSize = 0;

        int length = Array.getLength(targetArray);
        for (int i = 0; i < length; i++) {
            Object arrayElement = Array.get(targetArray, i);

            if (arrayElement != null) {
                Class<?> elementType = selectElementType(targetArray, arrayElement);

                FieldHandler fieldHandler = provider.provideHandlerFor(elementType);
                TargetField arrayField = new ArrayField("element[" + i + "]", i, elementType);

                ResultNodeBuilder child = fieldHandler.handleField(arrayField, targetArray);
                children.add(child);
                branchSize += child.getBranchSize();
            }
        }

        return new TargetObjectHandlingResult(children, branchSize);
    }


    private Class<?> selectElementType(@Nonnull Object targetArray, @Nonnull Object arrayElement) {
        return primitiveArrays.getOrDefault(
                targetArray.getClass(),
                arrayElement.getClass());
    }
}
