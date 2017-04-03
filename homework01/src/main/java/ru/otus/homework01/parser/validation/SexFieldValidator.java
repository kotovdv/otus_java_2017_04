package ru.otus.homework01.parser.validation;

import ru.otus.homework01.model.Sex;

/**
 * @author Dmitriy Kotov
 */
public class SexFieldValidator extends EnumBasedFieldValidator<Sex> {
    public SexFieldValidator() {
        super("Sex", Sex.class);
    }
}