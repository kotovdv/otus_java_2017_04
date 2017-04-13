package ru.otus.homework02.measurer.tool.misc;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Dmitriy Kotov
 */
public class IdentityGenerator {

    private AtomicInteger counter = new AtomicInteger(0);

    public int next() {
        return counter.incrementAndGet();
    }

    public void clear() {
        counter.set(0);
    }
}