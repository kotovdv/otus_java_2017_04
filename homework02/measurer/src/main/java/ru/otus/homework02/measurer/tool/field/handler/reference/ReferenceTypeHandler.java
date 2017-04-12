package ru.otus.homework02.measurer.tool.field.handler.reference;

import ru.otus.homework02.measurer.tool.ObjectShallowSizeMeter;
import ru.otus.homework02.measurer.tool.field.FieldVisitor;
import ru.otus.homework02.measurer.tool.field.handler.FieldHandler;
import ru.otus.homework02.measurer.tool.field.handler.FieldHandlerProvider;
import ru.otus.homework02.measurer.tool.field.target.ReflectionField;
import ru.otus.homework02.measurer.tool.field.target.TargetField;
import ru.otus.homework02.measurer.tool.result.ObjectNodeBuilder;

import javax.annotation.Nonnull;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * @author Dmitriy Kotov
 */
public class ReferenceTypeHandler extends FieldHandler {

    public ReferenceTypeHandler(@Nonnull ObjectShallowSizeMeter sizeMeter,
                                @Nonnull FieldHandlerProvider provider,
                                @Nonnull FieldVisitor fieldVisitor) {
        super(sizeMeter, provider, fieldVisitor);
    }

    @Override
    public boolean canHandle(@Nonnull Class<?> type) {
        return !type.isPrimitive();
    }

    @Override
    public ObjectNodeBuilder handleField(final TargetField targetField, final Object source) {
        Object targetObject = targetField.getValue(source);

        ObjectNodeBuilder builder = new ObjectNodeBuilder().
                fieldName(targetField.getFieldName()).
                fieldType(targetField.getFieldType()).
                instanceType(getInstanceType(targetObject)).
                personalSize(sizeOf(targetField, source)).
                value(targetObject);

        if (targetObject == null) {
            return builder;
        }

        Optional<ObjectNodeBuilder> lastVisit = fieldVisitor.getLastVisit(targetObject);

        if (lastVisit.isPresent()) {
            return new ObjectNodeBuilder(lastVisit.get())
                    .branchSize(0)
                    .duplicate(true);
        }

        fieldVisitor.visit(targetObject, builder);
        TargetObjectHandlingResult handlingResult = handleTargetObject(targetObject);

        return builder.branchSize(calculateBranchSize(builder, handlingResult)).
                addChildren(handlingResult.getChildren());
    }

    protected TargetObjectHandlingResult handleTargetObject(@Nonnull Object targetObject) {
        List<ObjectNodeBuilder> children = new ArrayList<>();

        long branchSize = 0;

        Class<?> targetObjectClass = targetObject.getClass();

        while (targetObjectClass != null) {
            for (Field currentField : targetObjectClass.getDeclaredFields()) {
                if (isStaticField(currentField)) {
                    continue;
                }

                FieldHandler currentHandler = provider.provideHandlerFor(currentField.getType());

                TargetField targetField = new ReflectionField(currentField);
                ObjectNodeBuilder child = currentHandler.handleField(targetField, targetObject);
                branchSize += child.getBranchSize();

                children.add(child);
            }

            targetObjectClass = targetObjectClass.getSuperclass();
        }

        return new TargetObjectHandlingResult(children, branchSize);
    }

    private Class<?> getInstanceType(Object targetObject) {
        return targetObject != null
                ? targetObject.getClass()
                : null;
    }

    private long calculateBranchSize(ObjectNodeBuilder builder, TargetObjectHandlingResult handlingResult) {
        return builder.getPersonalSize() + handlingResult.getBranchSize();
    }
}