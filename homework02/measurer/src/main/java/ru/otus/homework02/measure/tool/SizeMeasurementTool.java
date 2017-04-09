package ru.otus.homework02.measure.tool;

import ru.otus.homework02.measure.tool.handler.FieldHandlerProvider;
import ru.otus.homework02.measure.tool.handler.concrete.ReferenceTypeHandler;
import ru.otus.homework02.measure.tool.result.Result;
import ru.otus.homework02.measure.tool.result.ResultNode;

import java.lang.reflect.Field;

/**
 * @author Dmitriy Kotov
 */
public class SizeMeasurementTool {

    private final ReferenceTypeHandler startingHandler = new ReferenceTypeHandler(
            FieldHandlerProvider.produceProvider()
    );

    public Result measure(Object instance) {
        MeasurementTarget target = new MeasurementTarget(instance);
        ResultNode rootNode = startingHandler.handleField(
                target.getTargetField(),
                target
        );

        return new Result(rootNode);
    }


    private static class MeasurementTarget {
        public final Object target;

        private MeasurementTarget(Object target) {
            this.target = target;
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