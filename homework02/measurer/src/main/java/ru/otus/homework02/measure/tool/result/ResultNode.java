package ru.otus.homework02.measure.tool.result;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.emptyList;

/**
 * @author Dmitriy Kotov
 */
public class ResultNode {

    private final long personalSize;
    private final long branchSize;
    private final Field field;
    private List<ResultNode> children = new ArrayList<>();

    public ResultNode(Field field, long personalSize, long branchSize, List<ResultNode> children) {
        this.personalSize = personalSize;
        this.field = field;
        this.branchSize = branchSize;
        this.children.addAll(children != null ? children : emptyList());
    }

    public long getPersonalSize() {
        return personalSize;
    }

    public long getBranchSize() {
        return branchSize;
    }

    public Field getField() {
        return field;
    }

    public List<ResultNode> getChildren() {
        return Collections.unmodifiableList(children);
    }
}