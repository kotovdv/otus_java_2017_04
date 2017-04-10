package ru.otus.homework02.measure.tool.handler.reference;

import ru.otus.homework02.measure.tool.result.ResultNodeBuilder;

import java.util.Collection;

/**
 * @author Dmitriy Kotov
 */
public class TargetObjectHandlingResult {

    private final Collection<ResultNodeBuilder> children;
    private final long branchSize;

    public TargetObjectHandlingResult(Collection<ResultNodeBuilder> children, long branchSize) {
        this.children = children;
        this.branchSize = branchSize;
    }

    public Collection<ResultNodeBuilder> getChildren() {
        return children;
    }

    public long getBranchSize() {
        return branchSize;
    }
}