package ru.otus.homework01.parser.validation;

import ru.otus.homework01.exception.CSVFieldValidationFailedException;

import static org.apache.commons.lang3.EnumUtils.isValidEnum;

/**
 * @author Dmitriy Kotov
 */
public abstract class EnumBasedFieldValidator<E extends Enum<E>> extends CSVFieldValidator {

    private final Class<E> enumClass;

    protected EnumBasedFieldValidator(String fieldName, Class<E> enumClass) {
        super(fieldName);
        this.enumClass = enumClass;
    }

    @Override
    protected void doCustomValidation(String value, long rowNumber) {
        if (isInvalidEnumValue(value)) {
            throw new CSVFieldValidationFailedException(rowNumber,
                    fieldName,
                    value,
                    "does not match any of enum values"
            );
        }
    }

    private boolean isInvalidEnumValue(String value) {
        return !isValidEnum(enumClass, cleanseValue(value));
    }

    private String cleanseValue(String value) {
        return value != null
                ? value.trim().toUpperCase()
                : null;
    }
}
