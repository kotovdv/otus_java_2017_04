package ru.otus.homework02.measure.tool.field;

import java.lang.reflect.Array;

/**
 * @author Dmitriy Kotov
 */
public class ArrayField implements TargetField {

    private final String customName;
    private final Class<?> arrayType;
    private final int index;

    public ArrayField(String customName, int index, Class<?> arrayType) {
        this.customName = customName;
        this.index = index;
        this.arrayType = arrayType;
    }

    @Override
    public String getFieldName() {
        return customName;
    }

    @Override
    public Class<?> getFieldType() {
        return arrayType;
    }

    @Override
    public Object getValue(Object source) {
        return Array.get(source, index);
    }
}