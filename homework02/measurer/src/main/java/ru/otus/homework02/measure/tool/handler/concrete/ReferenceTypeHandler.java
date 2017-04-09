package ru.otus.homework02.measure.tool.handler.concrete;

import ru.otus.homework02.measure.tool.handler.FieldHandler;
import ru.otus.homework02.measure.tool.handler.FieldHandlerProvider;
import ru.otus.homework02.measure.tool.result.ResultNode;

import javax.annotation.Nonnull;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static java.lang.reflect.Modifier.isStatic;
import static ru.otus.homework02.agent.Agent.getObjectSize;

/**
 * @author Dmitriy Kotov
 */
public class ReferenceTypeHandler extends FieldHandler {

    public ReferenceTypeHandler(FieldHandlerProvider provider) {
        super(provider);
    }

    @Override
    public ResultNode handleField(final Field targetField, final Object source) {
        Object targetObject = getFieldValue(targetField, source);

        List<ResultNode> children = new ArrayList<>();

        long branchSize = 0L;

        if (targetObject != null) {
            for (Field currentField : getDeclaredFields(targetObject)) {
                if (isStaticField(currentField)) {
                    continue;
                }

                FieldHandler handler = provider.provideHandlerFor(currentField.getType());

                ResultNode child = handler.handleField(currentField, targetObject);
                branchSize += child.getBranchSize();

                children.add(child);
            }
        }
        Class<?> targetType = targetField.getType();
        FieldHandler handler = provider.provideHandlerFor(targetType);

        long personalSize = handler.size(targetField, source);

        return new ResultNode(
                targetField,
                personalSize,
                branchSize > 0L ? branchSize : personalSize,
                children
        );
    }


    @Override
    public long size(@Nonnull Field instanceField, Object source) {
        Object fieldObject = getFieldValue(instanceField, source);

        return fieldObject != null
                ? getObjectSize(fieldObject)
                : calculateEmptyReferenceSize();
    }

    private long calculateEmptyReferenceSize() {
        return 0L;
    }


    @Override
    public boolean canHandle(Class<?> type) {
        return true;
    }

    private Field[] getDeclaredFields(Object instance) {
        return instance.getClass().getDeclaredFields();
    }

    private boolean isStaticField(Field declaredField) {
        return isStatic(declaredField.getModifiers());
    }
}