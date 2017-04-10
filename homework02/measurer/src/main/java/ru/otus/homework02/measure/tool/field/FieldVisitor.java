package ru.otus.homework02.measure.tool.field;

import ru.otus.homework02.exception.measure.tool.field.FieldWasPreviouslyVisitedException;
import ru.otus.homework02.measure.tool.result.ResultNodeBuilder;

import javax.annotation.Nonnull;
import java.util.IdentityHashMap;
import java.util.Optional;

/**
 * @author Dmitriy Kotov
 */
public class FieldVisitor {

    private IdentityHashMap<Object, ResultNodeBuilder> identityHashMap = new IdentityHashMap<>();

    public void visit(@Nonnull Object object, @Nonnull ResultNodeBuilder resultNode) {
        if (getLastVisit(object).isPresent()) {
            throw new FieldWasPreviouslyVisitedException(
                    resultNode.getFieldName(),
                    resultNode.getFieldType()
            );
        }

        identityHashMap.put(object, resultNode);
    }

    @Nonnull
    public Optional<ResultNodeBuilder> getLastVisit(Object target) {
        return Optional.ofNullable(identityHashMap.get(target));
    }

}