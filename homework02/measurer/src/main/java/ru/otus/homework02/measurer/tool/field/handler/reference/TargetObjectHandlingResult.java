package ru.otus.homework02.measurer.tool.field.handler.reference;

import ru.otus.homework02.measurer.tool.result.ObjectNodeBuilder;

import java.util.Collection;

/**
 * @author Dmitriy Kotov
 */
public class TargetObjectHandlingResult {

    private final long branchSize;
    private final Collection<ObjectNodeBuilder> children;

    public TargetObjectHandlingResult(Collection<ObjectNodeBuilder> children, long branchSize) {
        this.children = children;
        this.branchSize = branchSize;
    }

    public Collection<ObjectNodeBuilder> getChildren() {
        return children;
    }

    public long getBranchSize() {
        return branchSize;
    }
}