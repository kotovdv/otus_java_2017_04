package ru.otus.homework02.measure.tool.result;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.emptyList;

/**
 * @author Dmitriy Kotov
 */
public class ResultNode {

    private final String fieldName;
    private final Class<?> type;
    private final Object value;
    private final long personalSize;
    private final long branchSize;
    private List<ResultNode> children = new ArrayList<>();

    public ResultNode(String fieldName,
                      Class<?> type,
                      Object value,
                      long personalSize,
                      long branchSize,
                      List<ResultNode> children) {

        this.fieldName = fieldName;
        this.type = type;
        this.value = value;
        this.personalSize = personalSize;
        this.branchSize = branchSize;
        this.children.addAll(children != null ? children : emptyList());
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
}