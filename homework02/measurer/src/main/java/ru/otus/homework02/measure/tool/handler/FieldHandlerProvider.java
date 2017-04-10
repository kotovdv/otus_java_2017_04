package ru.otus.homework02.measure.tool.handler;

import ru.otus.homework02.exception.NoSuitableFieldHandlerFoundException;
import ru.otus.homework02.measure.tool.handler.primitive.PrimitiveTypeHandler;
import ru.otus.homework02.measure.tool.handler.reference.ReferenceTypeHandler;

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
        provider.handlers.add(new PrimitiveTypeHandler(provider, fieldVisitor));
        provider.handlers.add(new ReferenceTypeHandler(provider, fieldVisitor));

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