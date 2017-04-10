package ru.otus.homework02.measure.tool.handler.reference;

import ru.otus.homework02.measure.tool.handler.FieldHandler;
import ru.otus.homework02.measure.tool.handler.FieldHandlerProvider;
import ru.otus.homework02.measure.tool.handler.FieldVisitor;
import ru.otus.homework02.measure.tool.memory.MemorySpecification;
import ru.otus.homework02.measure.tool.result.ResultNodeBuilder;

import javax.annotation.Nonnull;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static ru.otus.homework02.agent.Agent.getObjectSize;

/**
 * @author Dmitriy Kotov
 */
public class ReferenceTypeHandler extends FieldHandler {

    private final MemorySpecification memorySpecification = MemorySpecification.getCurrentSpecification();

    public ReferenceTypeHandler(@Nonnull FieldHandlerProvider provider, @Nonnull FieldVisitor fieldVisitor) {
        super(provider, fieldVisitor);
    }

    @Override
    public boolean canHandle(Class<?> type) {
        return !type.isPrimitive();
    }

    @Override
    public long size(@Nonnull Field instanceField, Object source) {
        Object fieldObject = getFieldValue(instanceField, source);

        return fieldObject != null
                ? getObjectSize(fieldObject)
                : calculateEmptyReferenceSize();
    }

    @Override
    public ResultNodeBuilder handleField(final Field targetField, final Object source) {
        Object targetObject = getFieldValue(targetField, source);

        ResultNodeBuilder builder = new ResultNodeBuilder().
                fieldName(targetField.getName()).
                fieldType(targetField.getType()).
                personalSize(size(targetField, source)).
                value(targetObject);

        if (targetObject == null) {
            return builder;
        }

        Optional<ResultNodeBuilder> lastVisit = fieldVisitor.getLastVisit(targetObject);

        if (lastVisit.isPresent()) {
            return new ResultNodeBuilder(lastVisit.get())
                    .duplicate(true);
        }

        fieldVisitor.visit(targetObject, builder);
        TargetObjectHandlingResult handlingResult = handleTargetObject(targetObject);

        return builder.branchSize(calculateBranchSize(builder, handlingResult)).
                addChildren(handlingResult.getChildren());
    }

    private long calculateBranchSize(ResultNodeBuilder builder, TargetObjectHandlingResult handlingResult) {
        return builder.getPersonalSize() + handlingResult.getBranchSize();
    }

    private TargetObjectHandlingResult handleTargetObject(@Nonnull Object targetObject) {
        List<ResultNodeBuilder> children = new ArrayList<>();

        long branchSize = 0;

        Class<?> targetObjectClass = targetObject.getClass();

        do {
            for (Field currentField : getDeclaredFields(targetObjectClass)) {
                if (isStaticField(currentField)) {
                    continue;
                }

                FieldHandler currentHandler = provider.provideHandlerFor(currentField.getType());

                ResultNodeBuilder child = currentHandler.handleField(currentField, targetObject);
                branchSize += child.getBranchSize();

                children.add(child);
            }

            targetObjectClass = targetObjectClass.getSuperclass();
        } while (targetObjectClass != null);

        return new TargetObjectHandlingResult(children, branchSize);
    }

    private long calculateEmptyReferenceSize() {
        return memorySpecification.getReferenceSize();
    }

    private Field[] getDeclaredFields(Class<?> classType) {
        return classType.getDeclaredFields();
    }
}