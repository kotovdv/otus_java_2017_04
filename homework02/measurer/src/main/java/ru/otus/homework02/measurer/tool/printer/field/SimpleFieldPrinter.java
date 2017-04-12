package ru.otus.homework02.measurer.tool.printer.field;

import ru.otus.homework02.measurer.tool.result.ObjectNode;

/**
 * @author Dmitriy Kotov
 */
public abstract class SimpleFieldPrinter implements FieldPrinter {

    private final String name;

    public SimpleFieldPrinter(String name) {
        this.name = name;
    }

    public void appendTo(StringBuilder stringBuilder, ObjectNode objectNode) {
        stringBuilder.append(element(name, getValue(objectNode)));
    }

    protected abstract String getValue(ObjectNode objectNode);

    private String element(String name, Object value) {
        return name +
                "= [" +
                value +
                "]";
    }
}