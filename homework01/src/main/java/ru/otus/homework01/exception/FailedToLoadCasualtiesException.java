package ru.otus.homework01.exception;

/**
 * @author Dmitriy Kotov
 */
public class FailedToLoadCasualtiesException extends RuntimeException {

    public FailedToLoadCasualtiesException(String message) {
        super(message);
    }

    public FailedToLoadCasualtiesException(String accidentName, Throwable t) {
        super(String.format("Failed to load casualties from accident [%s]", accidentName), t);
    }
}
