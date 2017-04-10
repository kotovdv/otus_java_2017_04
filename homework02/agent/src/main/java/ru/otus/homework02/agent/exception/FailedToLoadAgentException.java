package ru.otus.homework02.agent.exception;

/**
 * @author Dmitriy Kotov
 */
public class FailedToLoadAgentException extends RuntimeException {

    public FailedToLoadAgentException(Class<?> agentClass) {
        super(String.format("Failed to load agent class [%s]", agentClass.getName()));
    }
}