package ru.otus.homework02.measure.tool;

import ru.otus.homework02.agent.Agent;

import javax.annotation.Nonnull;
import java.lang.instrument.Instrumentation;

/**
 * @author Dmitriy Kotov
 */
public class ObjectShallowSizeMeter {

    private final Instrumentation instrumentation;

    private ObjectShallowSizeMeter(@Nonnull Instrumentation instrumentation) {
        this.instrumentation = instrumentation;
    }

    public static ObjectShallowSizeMeter create() {
        return new ObjectShallowSizeMeter(Agent.getInstrumentation());
    }

    public long getObjectSize(final Object object) {
        return instrumentation.getObjectSize(object);
    }
}