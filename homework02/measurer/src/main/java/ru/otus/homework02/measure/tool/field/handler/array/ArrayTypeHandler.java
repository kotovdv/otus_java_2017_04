package ru.otus.homework02.measure.tool.field.handler.array;

import ru.otus.homework02.measure.tool.ObjectShallowSizeMeter;
import ru.otus.homework02.measure.tool.field.ArrayField;
import ru.otus.homework02.measure.tool.field.FieldVisitor;
import ru.otus.homework02.measure.tool.field.TargetField;
import ru.otus.homework02.measure.tool.field.handler.FieldHandler;
import ru.otus.homework02.measure.tool.field.handler.FieldHandlerProvider;
import ru.otus.homework02.measure.tool.field.handler.reference.ReferenceTypeHandler;
import ru.otus.homework02.measure.tool.field.handler.reference.TargetObjectHandlingResult;
import ru.otus.homework02.measure.tool.result.ResultNodeBuilder;

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
    public boolean canHandle(Class<?> type) {
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

                FieldHandler fieldHandler = provider.provideHandlerFor(arrayElement.getClass());
                TargetField arrayField = new ArrayField("element[" + i + "]", i, arrayElement.getClass());

                ResultNodeBuilder child = fieldHandler.handleField(arrayField, targetArray);
                children.add(child);
                branchSize += child.getBranchSize();
            }
        }

        return new TargetObjectHandlingResult(children, branchSize);
    }
}
