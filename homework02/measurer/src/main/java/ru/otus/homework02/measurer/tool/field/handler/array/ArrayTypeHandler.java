package ru.otus.homework02.measurer.tool.field.handler.array;

import ru.otus.homework02.measurer.tool.ObjectShallowSizeMeter;
import ru.otus.homework02.measurer.tool.field.FieldVisitor;
import ru.otus.homework02.measurer.tool.field.handler.FieldHandler;
import ru.otus.homework02.measurer.tool.field.handler.FieldHandlerProvider;
import ru.otus.homework02.measurer.tool.field.handler.reference.ReferenceTypeHandler;
import ru.otus.homework02.measurer.tool.field.handler.reference.TargetObjectHandlingResult;
import ru.otus.homework02.measurer.tool.field.target.ArrayField;
import ru.otus.homework02.measurer.tool.field.target.TargetField;
import ru.otus.homework02.measurer.tool.result.ObjectNodeBuilder;

import javax.annotation.Nonnull;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Dmitriy Kotov
 */
public final class ArrayTypeHandler extends ReferenceTypeHandler {

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
        List<ObjectNodeBuilder> children = new ArrayList<>();

        long branchSize = 0;

        int length = Array.getLength(targetArray);
        for (int i = 0; i < length; i++) {
            Object arrayElement = Array.get(targetArray, i);

            if (arrayElement != null) {
                Class<?> elementType = targetArray.getClass().getComponentType();

                FieldHandler fieldHandler = provider.provideHandlerFor(elementType);
                TargetField arrayField = new ArrayField("element[" + i + "]", i, elementType);

                ObjectNodeBuilder child = fieldHandler.handleField(arrayField, targetArray);
                children.add(child);
                branchSize += child.getBranchSize();
            }
        }

        return new TargetObjectHandlingResult(children, branchSize);
    }
}
