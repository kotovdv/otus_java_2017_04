package ru.otus.homework01.parser.validation;

import org.apache.commons.csv.CSVRecord;
import ru.otus.homework01.exception.CSVFieldValidationFailedException;

import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * @author Dmitriy Kotov
 */
public abstract class CSVFieldValidator {

    protected final String fieldName;

    protected CSVFieldValidator(String fieldName) {
        this.fieldName = fieldName;
    }

    public final void validate(CSVRecord csvRecord) throws CSVFieldValidationFailedException {
        String value = csvRecord.get(fieldName);
        long rowNumber = csvRecord.getRecordNumber();

        if (isBlank(value)) {
            throw new CSVFieldValidationFailedException(
                    rowNumber,
                    fieldName,
                    value,
                    "mandatory field not filled"
            );
        }

        doCustomValidation(value, rowNumber);
    }

    protected void doCustomValidation(String value, long rowNumber) {
        //For subclass to override if some additional logic is required
    }
}
