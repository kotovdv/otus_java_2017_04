package ru.otus.homework02.measurer.tool.result;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;
import static java.util.stream.Collectors.toList;

/**
 * @author Dmitriy Kotov
 */
public class ObjectNodeBuilder {

    private Integer id;
    private boolean isDuplicate;
    private String fieldName;
    private Class<?> fieldType;
    private Class<?> instanceType;
    private Object value;
    private Long personalSize;
    private long branchSize;
    private List<ObjectNodeBuilder> children = new ArrayList<>();

    public ObjectNodeBuilder() {
    }

    public ObjectNodeBuilder(ObjectNodeBuilder builder) {
        this.id = builder.getId();
        this.isDuplicate = builder.isDuplicate();
        this.fieldName = builder.getFieldName();
        this.fieldType = builder.getFieldType();
        this.instanceType = builder.getInstanceType();
        this.value = builder.getValue();
        this.personalSize = builder.getPersonalSize();
        this.branchSize = builder.getBranchSize();
        this.children.addAll(builder.getChildren());
    }

    public int getId() {
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

    public Long getPersonalSize() {
        return personalSize;
    }

    public long getBranchSize() {
        return branchSize;
    }

    public boolean isDuplicate() {
        return isDuplicate;
    }

    public List<ObjectNodeBuilder> getChildren() {
        return children;
    }

    public ObjectNodeBuilder id(int id) {
        this.id = id;

        return this;
    }

    public ObjectNodeBuilder fieldName(@Nonnull String fieldName) {
        this.fieldName = fieldName;

        return this;
    }

    public ObjectNodeBuilder fieldType(@Nonnull Class<?> type) {
        this.fieldType = type;

        return this;
    }

    public ObjectNodeBuilder instanceType(Class<?> type) {
        this.instanceType = type;

        return this;
    }

    public ObjectNodeBuilder value(Object value) {
        this.value = value;

        return this;
    }

    public ObjectNodeBuilder personalSize(long personalSize) {
        this.personalSize = personalSize;

        return this;
    }

    public ObjectNodeBuilder branchSize(long branchSize) {
        this.branchSize = branchSize;

        return this;
    }

    public ObjectNodeBuilder duplicate(boolean value) {
        this.isDuplicate = value;

        return this;
    }

    public ObjectNodeBuilder addChildren(@Nonnull Collection<ObjectNodeBuilder> children) {
        this.children.addAll(children);

        return this;
    }

    public ObjectNode build() {
        checkNotNull(id, "Id must be filled");
        checkNotNull(fieldName, "Field name must be filled");
        checkNotNull(personalSize, "Personal size must be filled");

        return new ObjectNode(this,
                children.stream().
                        map(ObjectNodeBuilder::build).
                        collect(toList())
        );
    }
}
