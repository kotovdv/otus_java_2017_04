package ru.otus.homework02.measure.tool.handler;

import ru.otus.homework02.exception.NoSuitableFieldHandlerFoundException;
import ru.otus.homework02.measure.tool.handler.concrete.PrimitiveTypeHandler;
import ru.otus.homework02.measure.tool.handler.concrete.ReferenceTypeHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dmitriy Kotov
 */
public class FieldHandlerProvider {

    private List<FieldHandler> handlers = new ArrayList<>();

    public static FieldHandlerProvider produceProvider() {
        FieldHandlerProvider provider = new FieldHandlerProvider();

        provider.handlers.add(new PrimitiveTypeHandler(provider));
//        provider.handlers.add(new CollectionTypeHandler(provider));
        provider.handlers.add(new ReferenceTypeHandler(provider));

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