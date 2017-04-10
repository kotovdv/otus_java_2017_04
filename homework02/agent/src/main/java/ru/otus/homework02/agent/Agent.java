package ru.otus.homework02.agent;

import ru.otus.homework02.agent.exception.FailedToLoadAgentException;

import java.lang.instrument.Instrumentation;

import static com.ea.agentloader.AgentLoader.loadAgentClass;

/**
 * @author Dmitriy Kotov
 */
public class Agent {

    private static volatile Instrumentation globalInstrumentation;

    /**
     * Entry point for AgentLoader library
     */
    public static void agentmain(String agentArgs, Instrumentation inst) {
        initialize(inst);
    }

    /**
     * Entry point for -javaagent option startup
     */
    public static void premain(String agentArgs, Instrumentation inst) {
        initialize(inst);
    }

    private static void initialize(Instrumentation instrumentation) {
        globalInstrumentation = instrumentation;
    }

    public static Instrumentation getInstrumentation() {
        if (globalInstrumentation == null) {
            synchronized (Agent.class) {
                if (globalInstrumentation == null) {
                    safeInitialize();
                }
            }
        }

        return globalInstrumentation;
    }

    private static void safeInitialize() {
        loadAgentClass(Agent.class.getName(), "");
        if (globalInstrumentation == null) {
            throw new FailedToLoadAgentException(Agent.class);
        }
    }


}
