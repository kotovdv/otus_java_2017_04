package ru.otus.homework01.exception;

/**
 * @author Dmitriy Kotov
 */
public class CSVFieldValidationFailedException extends Homework01Exception {

    public CSVFieldValidationFailedException(long rowNumber, String fieldName, String fieldValue, String reason) {
        super("Validation failed at row [" + rowNumber + "]\n" +
                "field [" + fieldName + "]\n" +
                "value [" + fieldValue + "]\n" +
                "reason [" + reason + "]");
    }
}
