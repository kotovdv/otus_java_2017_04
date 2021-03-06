package ru.otus.homework02.measurer.tool.result;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.emptyList;

/**
 * @author Dmitriy Kotov
 */
public class ObjectNode {

    private final long id;
    private final boolean isDuplicate;
    private final String fieldName;
    private final Class<?> fieldType;
    private final Class<?> instanceType;
    private final Object value;
    private final long personalSize;
    private final long branchSize;
    private List<ObjectNode> children = new ArrayList<>();

    public ObjectNode(ObjectNodeBuilder builder, List<ObjectNode> children) {
        this.id = builder.getId();
        this.isDuplicate = builder.isDuplicate();
        this.fieldName = builder.getFieldName();
        this.fieldType = builder.getFieldType();
        this.instanceType = builder.getInstanceType();
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
        return fieldType;
    }

    public Class<?> getInstanceType() {
        return instanceType;
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

    public List<ObjectNode> getChildren() {
        return Collections.unmodifiableList(children);
    }

    public boolean isDuplicate() {
        return isDuplicate;
    }

    @Override
    public String toString() {
        return "ObjectNode{" +
                "id=" + id +
                ", fieldName='" + fieldName + '\'' +
                ", fieldType=" + fieldType +
                '}';
    }
}