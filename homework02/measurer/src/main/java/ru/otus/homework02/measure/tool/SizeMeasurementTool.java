package ru.otus.homework02.measure.tool;

import ru.otus.homework02.measure.tool.field.handler.FieldHandler;
import ru.otus.homework02.measure.tool.field.handler.FieldHandlerProvider;
import ru.otus.homework02.measure.tool.result.Result;
import ru.otus.homework02.measure.tool.result.ResultNodeBuilder;

import java.lang.reflect.Field;

/**
 * @author Dmitriy Kotov
 */
public class SizeMeasurementTool {

    private final FieldHandlerProvider provider = FieldHandlerProvider.produceProvider();

    public Result measure(Object instance) {
        if (instance == null) {
            throw new IllegalArgumentException("Measured instance should be not null");
        }

        MeasurementTarget target = new MeasurementTarget(instance);

        FieldHandler handler = provider.provideHandlerFor(target.targetClass);
        ResultNodeBuilder builder = handler.handleField(
                target.getTargetField(),
                target
        );

        return new Result(builder.build());
    }


    private static class MeasurementTarget {
        public final Object target;
        public final Class<?> targetClass;

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