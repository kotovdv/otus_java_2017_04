package ru.otus.homework01.parser.validation;

import ru.otus.homework01.model.Outcome;

/**
 * @author Dmitriy Kotov
 */
public class OutcomeFieldValidator extends EnumBasedFieldValidator<Outcome> {
    public OutcomeFieldValidator() {
        super("Outcome", Outcome.class);
    }
}
