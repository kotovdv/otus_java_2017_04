package ru.otus.homework02.measurer.tool.result;

import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;

/**
 * @author Dmitriy Kotov
 */
public class ObjectTree {

    private final LocalDateTime measurementDt;
    private final ObjectNode rootNode;

    public ObjectTree(ObjectNode rootNode) {
        this(now(), rootNode);
    }

    public ObjectTree(LocalDateTime measurementDt,
                      ObjectNode node) {
        this.measurementDt = measurementDt;
        this.rootNode = node;
    }

    public LocalDateTime getMeasurementDt() {
        return measurementDt;
    }

    public long getTotalSize() {
        return rootNode.getBranchSize();
    }

    public ObjectNode getRootNode() {
        return rootNode;
    }

    @Override
    public String toString() {
        return "ObjectTree{" +
                "measurementDt=" + measurementDt +
                ", rootNode=" + rootNode +
                '}';
    }
}