package ru.otus.homework02.measurer.tool.field;

import ru.otus.homework02.measurer.exception.tool.field.FieldWasPreviouslyVisitedException;
import ru.otus.homework02.measurer.tool.result.ObjectNodeBuilder;

import javax.annotation.Nonnull;
import java.util.IdentityHashMap;
import java.util.Optional;

/**
 * @author Dmitriy Kotov
 */
public class FieldVisitor {

    private IdentityHashMap<Object, ObjectNodeBuilder> identityHashMap = new IdentityHashMap<>();

    public void visit(@Nonnull Object object, @Nonnull ObjectNodeBuilder builder) {
        if (getLastVisit(object).isPresent()) {
            throw new FieldWasPreviouslyVisitedException(
                    builder.getFieldName(),
                    builder.getFieldType()
            );
        }

        identityHashMap.put(object, builder);
    }

    @Nonnull
    public Optional<ObjectNodeBuilder> getLastVisit(Object target) {
        return Optional.ofNullable(identityHashMap.get(target));
    }

    public void clear() {
        this.identityHashMap.clear();
    }
}
