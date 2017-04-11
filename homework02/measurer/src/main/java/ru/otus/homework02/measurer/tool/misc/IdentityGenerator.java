package ru.otus.homework02.measurer.tool.misc;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Dmitriy Kotov
 */
public class IdentityGenerator {

    private static AtomicInteger counter = new AtomicInteger(0);

    public static int nextIdentity() {
        return counter.incrementAndGet();
    }
}