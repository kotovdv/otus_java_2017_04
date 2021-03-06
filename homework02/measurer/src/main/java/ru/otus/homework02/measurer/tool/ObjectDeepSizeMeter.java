package ru.otus.homework02.measurer.tool;

import ru.otus.homework02.measurer.tool.field.FieldVisitor;
import ru.otus.homework02.measurer.tool.field.handler.FieldHandler;
import ru.otus.homework02.measurer.tool.field.handler.FieldHandlerProvider;
import ru.otus.homework02.measurer.tool.field.target.ReflectionField;
import ru.otus.homework02.measurer.tool.misc.IdentityGenerator;
import ru.otus.homework02.measurer.tool.result.ObjectNodeBuilder;
import ru.otus.homework02.measurer.tool.result.ObjectTree;

import java.lang.reflect.Field;

import static ru.otus.homework02.measurer.tool.field.handler.FieldHandlerProvider.produceProvider;

/**
 * @author Dmitriy Kotov
 */
public class ObjectDeepSizeMeter {

    private final IdentityGenerator identityGenerator = new IdentityGenerator();
    private final FieldVisitor fieldVisitor = new FieldVisitor();
    private final FieldHandlerProvider provider = produceProvider(identityGenerator, fieldVisitor);

    public ObjectTree measure(Object instance) {
        if (instance == null) {
            throw new IllegalArgumentException("Measured instance should be not null");
        }

        MeasurementTarget target = new MeasurementTarget(instance);

        FieldHandler handler = provider.provideHandlerFor(target.targetClass);
        ObjectNodeBuilder builder = handler.handleField(
                new ReflectionField(target.getTargetField()),
                target
        );
        fieldVisitor.clear();
        identityGenerator.clear();

        return new ObjectTree(builder.build());
    }

    private static class MeasurementTarget {
        final Object target;
        final Class<?> targetClass;

        private MeasurementTarget(Object target) {
            this.target = target;
            this.targetClass = target.getClass();
        }

        private Field getTargetField() {
            try {
                return MeasurementTarget.class.getDeclaredField("target");
            } catch (NoSuchFieldException e) {
                throw new IllegalStateException("Missing target field for class [" + MeasurementTarget.class + "]");
            }
        }
    }
}