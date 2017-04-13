package ru.otus.homework02.measurer.tool.field.handler;

import ru.otus.homework02.measurer.exception.tool.field.handler.NoSuitableFieldHandlerFoundException;
import ru.otus.homework02.measurer.tool.ObjectShallowSizeMeter;
import ru.otus.homework02.measurer.tool.field.FieldVisitor;
import ru.otus.homework02.measurer.tool.field.handler.array.ArrayTypeHandler;
import ru.otus.homework02.measurer.tool.field.handler.primitive.PrimitiveTypeHandler;
import ru.otus.homework02.measurer.tool.field.handler.reference.ReferenceTypeHandler;
import ru.otus.homework02.measurer.tool.misc.IdentityGenerator;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Dmitriy Kotov
 */
public class FieldHandlerProvider {

    private final List<FieldHandler> handlers = new ArrayList<>();

    public static FieldHandlerProvider produceProvider(@Nonnull IdentityGenerator identityGenerator, @Nonnull FieldVisitor fieldVisitor) {
        FieldHandlerProvider provider = new FieldHandlerProvider();

        ObjectShallowSizeMeter sizeMeter = ObjectShallowSizeMeter.create();
        provider.handlers.add(new PrimitiveTypeHandler(identityGenerator, sizeMeter, provider, fieldVisitor));
        provider.handlers.add(new ArrayTypeHandler(identityGenerator, sizeMeter, provider, fieldVisitor));
        provider.handlers.add(new ReferenceTypeHandler(identityGenerator, sizeMeter, provider, fieldVisitor));

        return provider;
    }

    public FieldHandler provideHandlerFor(@Nonnull Class<?> type) {
        for (FieldHandler handler : handlers) {
            if (handler.canHandle(type)) {
                return handler;
            }
        }

        throw new NoSuitableFieldHandlerFoundException(type);
    }
}