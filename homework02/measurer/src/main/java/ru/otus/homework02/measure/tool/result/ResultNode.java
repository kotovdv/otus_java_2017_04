package ru.otus.homework02.measure.tool.result;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.emptyList;

/**
 * @author Dmitriy Kotov
 */
public class ResultNode {

    private final long id;
    private final boolean isDuplicate;
    private final String fieldName;
    private final Class<?> type;
    private final Object value;
    private final long personalSize;
    private final long branchSize;
    private List<ResultNode> children = new ArrayList<>();

    public ResultNode(ResultNodeBuilder builder, List<ResultNode> children) {
        this.id = builder.getId();
        this.isDuplicate = builder.isDuplicate();
        this.fieldName = builder.getFieldName();
        this.type = builder.getFieldType();
        this.value = builder.getValue();
        this.personalSize = builder.getPersonalSize();
        this.branchSize = builder.getBranchSize();
        this.children.addAll(children != null ? children : emptyList());
    }

    public long getId() {
        return id;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Class<?> getFieldType() {
        return type;
    }

    public Object getValue() {
        return value;
    }

    public long getPersonalSize() {
        return personalSize;
    }

    public long getBranchSize() {
        return branchSize;
    }

    public List<ResultNode> getChildren() {
        return Collections.unmodifiableList(children);
    }

    public boolean isDuplicate() {
        return isDuplicate;
    }
}