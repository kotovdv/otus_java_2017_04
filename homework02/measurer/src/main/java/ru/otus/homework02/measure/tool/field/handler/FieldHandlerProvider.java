package ru.otus.homework02.measure.tool.field.handler;

import ru.otus.homework02.exception.measure.tool.field.handler.NoSuitableFieldHandlerFoundException;
import ru.otus.homework02.measure.tool.ShallowObjectSizeMeter;
import ru.otus.homework02.measure.tool.field.FieldVisitor;
import ru.otus.homework02.measure.tool.field.handler.primitive.PrimitiveTypeHandler;
import ru.otus.homework02.measure.tool.field.handler.reference.ReferenceTypeHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dmitriy Kotov
 */
public class FieldHandlerProvider {

    private final List<FieldHandler> handlers = new ArrayList<>();

    public static FieldHandlerProvider produceProvider() {
        FieldHandlerProvider provider = new FieldHandlerProvider();

        FieldVisitor fieldVisitor = new FieldVisitor();
        ShallowObjectSizeMeter sizeMeter = ShallowObjectSizeMeter.create();
        provider.handlers.add(new PrimitiveTypeHandler(sizeMeter, provider, fieldVisitor));
        provider.handlers.add(new ReferenceTypeHandler(sizeMeter, provider, fieldVisitor));

        return provider;
    }

    public FieldHandler provideHandlerFor(Class<?> type) {
        for (FieldHandler handler : handlers) {
            if (handler.canHandle(type)) {
                return handler;
            }
        }

        throw new NoSuitableFieldHandlerFoundException(type);
    }
}