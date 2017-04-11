package ru.otus.homework02.measurer.tool.result;

import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;

/**
 * @author Dmitriy Kotov
 */
public class ObjectTree {

    private final LocalDateTime measurementDt;
    private final ResultNode rootNode;

    public ObjectTree(ResultNode rootNode) {
        this(now(), rootNode);
    }

    public ObjectTree(LocalDateTime measurementDt,
                      ResultNode node) {
        this.measurementDt = measurementDt;
        this.rootNode = node;
    }

    public LocalDateTime getMeasurementDt() {
        return measurementDt;
    }

    public long getTotalSize() {
        return rootNode.getBranchSize();
    }

    public ResultNode getRootNode() {
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