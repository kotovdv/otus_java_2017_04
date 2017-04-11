package ru.otus.homework02.measurer.tool.result;

import com.google.common.base.Preconditions;
import ru.otus.homework02.measurer.tool.misc.IdentityGenerator;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author Dmitriy Kotov
 */
public class ResultNodeBuilder {

    private int id = IdentityGenerator.nextIdentity();
    private boolean isDuplicate;
    private String fieldName;
    private Class<?> fieldType;
    private Class<?> instanceType;
    private Object value;
    private Long personalSize;
    private long branchSize;
    private List<ResultNodeBuilder> children = new ArrayList<>();

    public ResultNodeBuilder() {
    }

    public ResultNodeBuilder(ResultNodeBuilder builder) {
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

    public List<ResultNodeBuilder> getChildren() {
        return children;
    }

    public ResultNodeBuilder id(int id) {
        this.id = id;

        return this;
    }

    public ResultNodeBuilder fieldName(@Nonnull String fieldName) {
        this.fieldName = fieldName;

        return this;
    }

    public ResultNodeBuilder fieldType(@Nonnull Class<?> type) {
        this.fieldType = type;

        return this;
    }

    public ResultNodeBuilder instanceType(Class<?> type) {
        this.instanceType = type;

        return this;
    }

    public ResultNodeBuilder value(Object value) {
        this.value = value;

        return this;
    }

    public ResultNodeBuilder personalSize(long personalSize) {
        this.personalSize = personalSize;

        return this;
    }

    public ResultNodeBuilder branchSize(long branchSize) {
        this.branchSize = branchSize;

        return this;
    }

    public ResultNodeBuilder duplicate(boolean value) {
        this.isDuplicate = value;

        return this;
    }

    public ResultNodeBuilder addChildren(@Nonnull Collection<ResultNodeBuilder> children) {
        this.children.addAll(children);

        return this;
    }

    public ResultNode build() {
        Preconditions.checkNotNull(fieldName, "Field name must be filled");
        Preconditions.checkNotNull(personalSize, "Personal size must be filled");

        return new ResultNode(this,
                children.stream().map(ResultNodeBuilder::build)
                        .collect(toList())
        );
    }
}
