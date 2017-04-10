package ru.otus.homework02.measure.tool.field.handler.array;

import ru.otus.homework02.measure.tool.ObjectShallowSizeMeter;
import ru.otus.homework02.measure.tool.field.FieldVisitor;
import ru.otus.homework02.measure.tool.field.handler.FieldHandler;
import ru.otus.homework02.measure.tool.field.handler.FieldHandlerProvider;
import ru.otus.homework02.measure.tool.field.handler.reference.ReferenceTypeHandler;
import ru.otus.homework02.measure.tool.result.ResultNodeBuilder;

import javax.annotation.Nonnull;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Dmitriy Kotov
 */
public class ArrayHandler extends ReferenceTypeHandler {

    protected ArrayHandler(@Nonnull ObjectShallowSizeMeter sizeMeter,
                           @Nonnull FieldHandlerProvider provider,
                           @Nonnull FieldVisitor fieldVisitor) {
        super(sizeMeter, provider, fieldVisitor);
    }

    @Override
    public boolean canHandle(Class<?> type) {
        return type.isArray();
    }

    @Override
    public ResultNodeBuilder handleField(Field targetField, Object source) {
        Object targetArray = getFieldValue(targetField, source);

        if (targetField != null) {
            List<ResultNodeBuilder> arrayNodes = new ArrayList<>();

            int length = Array.getLength(targetArray);
            for (int i = 0; i < length; i++) {
                Object arrayElement = Array.get(targetArray, i);

                if (arrayElement != null) {
                    FieldHandler fieldHandler = provider.provideHandlerFor(arrayElement.getClass());
//                    fieldHandler.handleField()
                }
            }
        }

        return null;
    }
}
