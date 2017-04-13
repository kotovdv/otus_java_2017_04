package ru.otus.homework02.measurer;

public class Scenario {

    private final String scenarioName;
    private final Object measurementTarget;

    public Scenario(String scenarioName, Object measurementTarget) {
        this.scenarioName = scenarioName;
        this.measurementTarget = measurementTarget;
    }

    public String getScenarioName() {
        return scenarioName;
    }

    public Object getMeasurementTarget() {
        return measurementTarget;
    }
}