package ru.otus.homework02.measurer.exception.tool.memory;

import ru.otus.homework02.measurer.exception.Homework02Exception;

/**
 * @author Dmitriy Kotov
 */
public class UnableToDetermineJVMBitness extends Homework02Exception {

    public UnableToDetermineJVMBitness(String message) {
        super(message);
    }
}