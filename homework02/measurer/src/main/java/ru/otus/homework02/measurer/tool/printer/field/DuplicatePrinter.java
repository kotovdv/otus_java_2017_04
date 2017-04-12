package ru.otus.homework02.measurer.tool.printer.field;

import ru.otus.homework02.measurer.tool.result.ObjectNode;

/**
 * @author Dmitriy Kotov
 */
public class DuplicatePrinter implements FieldPrinter {

    @Override
    public void appendTo(StringBuilder stringBuilder, ObjectNode objectNode) {
        if (objectNode.isDuplicate()) {
            stringBuilder.append("[DUPLICATE]");
        }
    }
}