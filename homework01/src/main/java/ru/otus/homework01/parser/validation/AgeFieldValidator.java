package ru.otus.homework01.parser.validation;

import ru.otus.homework01.exception.CSVFieldValidationFailedException;

import static org.apache.commons.lang3.StringUtils.isNumeric;

/**
 * @author Dmitriy Kotov
 */
public class AgeFieldValidator extends CSVFieldValidator {
    public AgeFieldValidator() {
        super("Age");
    }

    @Override
    protected void doCustomValidation(String value, long rowNumber) {
        if (!isNumeric(value)) {
            throw new CSVFieldValidationFailedException(rowNumber,
                    fieldName,
                    value,
                    "age must contain numeric value");
        }
    }
}
