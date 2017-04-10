package ru.otus.homework02.measure.tool;

import ru.otus.homework02.agent.Agent;

import javax.annotation.Nonnull;
import java.lang.instrument.Instrumentation;

/**
 * @author Dmitriy Kotov
 */
public class ShallowObjectSizeMeter {

    private final Instrumentation instrumentation;

    private ShallowObjectSizeMeter(@Nonnull Instrumentation instrumentation) {
        this.instrumentation = instrumentation;
    }

    public static ShallowObjectSizeMeter create() {
        return new ShallowObjectSizeMeter(Agent.getInstrumentation());
    }

    public long getObjectSize(final Object object) {
        return instrumentation.getObjectSize(object);
    }
}