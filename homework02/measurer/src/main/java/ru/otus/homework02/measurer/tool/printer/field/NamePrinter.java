package ru.otus.homework02.measurer.tool.printer.field;

import ru.otus.homework02.measurer.tool.result.ObjectNode;

/**
 * @author Dmitriy Kotov
 */
public class NamePrinter extends SimpleFieldPrinter {
    public NamePrinter() {
        super("Name");
    }

    @Override
    protected String getValue(ObjectNode objectNode) {
        return objectNode.getFieldName();
    }
}