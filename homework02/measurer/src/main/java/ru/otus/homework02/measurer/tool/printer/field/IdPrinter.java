package ru.otus.homework02.measurer.tool.printer.field;

import ru.otus.homework02.measurer.tool.result.ObjectNode;

/**
 * @author Dmitriy Kotov
 */
public class IdPrinter extends SimpleFieldPrinter {
    public IdPrinter() {
        super("Id");
    }

    @Override
    protected String getValue(ObjectNode objectNode) {
        return Long.toString(objectNode.getId());
    }
}